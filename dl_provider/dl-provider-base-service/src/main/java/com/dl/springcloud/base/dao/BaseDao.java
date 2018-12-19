package com.dl.springcloud.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dl.springcloud.base.entity.Page;
import com.dl.springcloud.util.ReflectUtils;

@SuppressWarnings("unchecked")
public class BaseDao<E,PK extends Serializable>   {

	private Class<E> clazz;
	
	public BaseDao() {
        ParameterizedType t = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<E>) t.getActualTypeArguments()[0];
    }
 
	 /**
    * 实体管理对象
    */
   @PersistenceContext
   EntityManager entityManager;
   
   @Transactional(propagation = Propagation.REQUIRED)
   public void save(E bean) {
       entityManager.persist(bean);
   }

   @Transactional(propagation = Propagation.REQUIRED)
   public void update(E bean) {
       entityManager.merge(bean);
   }

   @Transactional(readOnly = true)
   public E findOne(PK id) {
       return entityManager.find(clazz, id);
   }

   /**
    * @param id
    * @return 小于0 表示删除失败 大与0表示删除成功
    */
   @Transactional(propagation = Propagation.REQUIRED)
   public int delete(PK id) {
       Query query = entityManager.createQuery("delete from " + clazz.getSimpleName() + " p where p.id = ?1");
       query.setParameter(1, id);
       return query.executeUpdate();
   }
   
   /**
    * 批量插入
    *
    * @param beans
    */
   @Transactional(propagation = Propagation.REQUIRED)
   public void batchSave(List<E> beans) {
       if (beans != null) {
           for (E bean : beans)
               entityManager.persist(bean);
           entityManager.flush();
           entityManager.clear();
       }

   }

   /**
    * 批量更新
    *
    * @param beans
    */
   @Transactional(propagation = Propagation.REQUIRED)
   public void batchUpdate(List<E> beans) {
       if (beans != null) {
           for (E bean : beans)
               entityManager.merge(bean);
           entityManager.flush();
           entityManager.clear();
       }
   }

   @Transactional(propagation = Propagation.REQUIRED)
   public int batchDelete(List<PK> ids) {
       StringBuffer hql = new StringBuffer("delete from " + clazz.getSimpleName() + " where id  in(:ids)");
       Query query = entityManager.createQuery(hql.toString());
       query.setParameter("ids", ids);
       return query.executeUpdate();
   }

   @Transactional(propagation = Propagation.REQUIRED)
   public void batchUpdateNotNull(List<E> beans) {
       if (beans == null || beans.size() == 0)
           return;
       List<Map<String, Object>> datas = new LinkedList<>();
       for (E bean : beans) {
           Map<String, Object> data = ReflectUtils.createMapForNotNull(bean);
           datas.add(data);
       }
       for (Map<String, Object> map : datas) {
           //拼接Hql语句
           StringBuffer hql = new StringBuffer("update " + clazz.getSimpleName() + "");
           Set<String> keys = map.keySet();
           boolean fist = true;
           for (String key : keys) {
               if (key.equals("id"))
                   continue;
               if (fist) {
                   hql.append(" set ").append(key + "=:" + key);
                   fist = false;
               } else {
                   hql.append("," + key + "=:" + key);
               }
           }
           hql.append(" where id=:id");
           Query query = entityManager.createQuery(hql.toString());

           //设置参数
           for (String key : keys) {
               Object value = map.get(key);
               query.setParameter(key, value);
           }
           query.executeUpdate();
       }
       entityManager.flush();
       entityManager.clear();

   }
   
   @Transactional(propagation = Propagation.REQUIRED)
   public int executeSql(String sql, Object... params) {
       Query query = entityManager.createNativeQuery(sql);
       setParameters(query,arrayKeyAndArrayValueToMap(regxKeys(sql), params));
       return query.executeUpdate();
   }
   
   @Transactional(propagation = Propagation.REQUIRED)
   public int executeSql(String sql, Map<String,Object> params) {
       Query query = entityManager.createNativeQuery(sql);
       setParameters(query,params);
       return query.executeUpdate();
   }
 
  
   /**
    * 分页查询
    * @param page 分页对象
    * @param params 查询参数
    * @param countSql 查询数量的sql
    * @param querySql 查询结果的sql
    * @param e 结果转成bean对象
    */
   public void queryResultPage(Page<E> page,Map<String,Object> params,String countSql,String querySql) {
	   
        Query countQuery = this.entityManager.createQuery(countSql,Long.class);
        this.setParameters(countQuery,params);
        Long count = (Long) countQuery.getSingleResult();
        page.setTotal(count.intValue());
        
        Query query = this.entityManager.createQuery(querySql,clazz);
        this.setParameters(query,params);
        query.setFirstResult(page.getPageNumber());
        query.setMaxResults((page.getPageNumber()+1)*page.getPageSize());
        page.setRows(query.getResultList());
        
   }
   
   /**
    * 分页查询
    * @param page 分页对象
    * @param params 查询参数
    * @param countSql 查询数量的sql
    * @param querySql 查询结果的sql
    * @param e 结果转成bean对象
    */
  @Transactional(readOnly = true)
  public void queryResultPageMap(Page<E> page,Map<String,Object> params,String countSql,String querySql) {
	   
        Query countQuery = this.entityManager.createQuery(countSql,Long.class);
        this.setParameters(countQuery,params);
        Long count = (Long) countQuery.getSingleResult();
        page.setTotal(count.intValue());
        
        Query query = this.entityManager.createQuery(querySql);
        this.setParameters(query,params);
        query.setFirstResult(page.getStartRows());
        query.setMaxResults(page.getEndRows());
        page.setRows(query.getResultList());
        
   }
   
   /**
    * 查询结果集
    * @param querySql 查询sql
    * @param params 查询参数
    * @return
    */
  @Transactional(readOnly = true)
  public List<Map<String,Object>> queryResultList(String querySql,Map<String,Object> params) {
       Query query = this.entityManager.createQuery(querySql);
       this.setParameters(query,params);
       return query.getResultList();
   }
   

   /**
    * 给hql参数设置值
    * @param query 查询
    * @param params 参数
    */
   private void setParameters(Query query,Map<String,Object> params){
       for(Map.Entry<String,Object> entry:params.entrySet()){
           query.setParameter(entry.getKey(),entry.getValue());
       }
   }
   
   
   /**
    * 查询结果集
    * @param querySql 查询sql
    * @param params 查询参数
    * @return
    */
   @Transactional(readOnly = true)
   public List<Map<String,Object>> queryResultListArray(String querySql,Object... params) {
	   return queryResultList(querySql,arrayKeyAndArrayValueToMap(regxKeys(querySql), params));
   }
   
   /**
    * 正则表达式解析sql 里面的 变量
    *
    * @param sql
    *            sql语句
    * @return
    * @add 2018-12-16 by dl 增加数组参数的可执行sql
    */
   private static String[] regxKeys(String sql) {
       List<String> result = new ArrayList<String>();
       sql += "	 ";
       sql = sql.replaceAll("\\s+", " ");
       Pattern pattern = Pattern.compile(".*?\\:([\\w\\d]{1,})[\\s\\,]");
       Matcher matcher = pattern.matcher(sql);
       int index = 0;
       while (matcher.find(index)) {
           String e = matcher.group(1);
           result.add(e);
           index = matcher.start(1);
       }
       return result.toArray(new String[result.size()]);
   }
   
   /**
    * 根据数组key和数组value转换成map
    *
    * @param keys
    *            map 的 key
    * @param values
    *            map 的 value
    * @return
    * @add 2018-12-16 by dl 增加数组参数的可执行sql
    */
   private static Map<String, Object> arrayKeyAndArrayValueToMap(String[] keys, Object[] values) {
       Map<String, Object> map = new HashMap<String, Object>();
       if (ArrayUtils.isEmpty(keys)) {
           //log.info("没有需要解析的参数！");
           return map;
       }
       if (ArrayUtils.isEmpty(values)) {
           //log.info("需要解析的参数没有值！");
           return map;
       }
       if (keys.length != values.length) {
           throw new RuntimeException("参数的个数跟绑定的值的个数不同");
       }
       for (int i = 0; i < keys.length; i++) {
           map.put(keys[i], values[i]);
       }
       return map;
   }
   
   
   
}
