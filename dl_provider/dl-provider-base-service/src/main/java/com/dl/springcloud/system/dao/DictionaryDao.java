package com.dl.springcloud.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dl.springcloud.base.dao.BaseDao;
import com.dl.springcloud.base.entity.Page;
import com.dl.springcloud.system.model.Dictionary;

@Repository
public class DictionaryDao  extends BaseDao<Dictionary, Integer> {
	
	@Autowired
	private IDictionaryDao dicDao;
	
    
    @Transactional(readOnly = true)
    public void findIncomeDailysByPage(Map<String,Object> params,Page<Dictionary> page) {
        StringBuilder countSelectSql = new StringBuilder();
        countSelectSql.append("select count(*) from t_dic po where 1=1 ");

        Map<String,Object> queryParams = new HashMap<String,Object>();
        
        StringBuilder selectSql = new StringBuilder();
        selectSql.append("from com.dl.springcloud.system.model.Dictionary po where 1=1 ");
        
        StringBuilder whereSql = new StringBuilder();
        if(StringUtils.isNotBlank((String)params.get("tCode"))){
            whereSql.append(" and t_Code=:tCode ");
            queryParams.put("tCode", params.get("tCode"));
        }
        if(StringUtils.isNotBlank((String)params.get("tText"))){
            whereSql.append(" and t_Text=:tText ");
            queryParams.put("tText", params.get("tText"));
        }
        super.queryResultPage(page, queryParams, countSelectSql.append(whereSql)+"", selectSql.append(whereSql)+"");
    }
   
    
    @Transactional
	public List<Dictionary> findAll() {
		return dicDao.findAll();
	}

	 
 
	
}
