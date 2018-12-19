package com.dl.springcloud.common;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

public class ReflectUtil {
	public static ReflectUtil anno = null;

	public static ReflectUtil getInstance() {
		if (anno == null) {
			anno = new ReflectUtil();
		}
		return anno;
	}
	
	/**
	 * 读取注解值
	 * 
	 * @param annotationClasss 处理Annotation类名称
	 * @param annotationField 处理Annotation类属性名称
	 * @param className 处理Annotation的使用类名称
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public static Object loadVlaue(String fileName, Object obj) {
		if(obj instanceof Map){
			Iterator it = ((Map) obj).keySet().iterator();
			while (it.hasNext()) {
				String key = (String)it.next();
				if(key.equals(fileName)){
					return ((Map) obj).get(key);
				}
			}
		}
		Field[] Fields = obj.getClass().getDeclaredFields();
		for (Field field : Fields) {
			if(fileName.indexOf(".")==-1){
				if(field.getName().equals(fileName)){
					field.setAccessible( true );
					try {
						return null==field.get(obj)?"":field.get(obj);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}else{
				if( fileName.indexOf(".") == fileName.lastIndexOf(".")){
					String [] path = fileName.split("\\.");
					if(field.getName().equals(path[0])){
						PropertyDescriptor pd;
						try {
							pd = new PropertyDescriptor(field.getName(),obj.getClass());
							Method getMethod = pd.getReadMethod();//获得get方法
							Object o = getMethod.invoke(obj);//执行get方法返回一个Object
							return loadVlaue(path[1],o);
						} catch (IntrospectionException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return "";
	}

	 
}
