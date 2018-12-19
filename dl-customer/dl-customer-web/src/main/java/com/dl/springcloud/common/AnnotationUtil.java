package com.dl.springcloud.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.dl.springcloud.base.entity.TempType;


public class AnnotationUtil {
	public static AnnotationUtil anno = null;

	public static AnnotationUtil getInstance() {
		if (anno == null) {
			anno = new AnnotationUtil();
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
	public Map<String, String> loadVlaue(String annotationField, String className) throws Exception {

		System.out.println("处理Annotation类属性名称  === "+annotationField);
		System.out.println("处理Annotation的调用类名称  === "+className);
		Map<String, String> map = new HashMap<String, String>();
		Field[] Fields = Class.forName(className).getDeclaredFields();
		for (Field field : Fields) {
			if (field.isAnnotationPresent(TempType.class)) {
				Annotation p = field.getAnnotation(TempType.class);
				if(p!=null){
					System.out.println(field.getName()+"---------->>"+field.getAnnotation(TempType.class).bl());
				} else{
					System.out.println(field.getName()+"---------->>null");
				}
			}
		}
		System.out.println("map数量  === " + map.size());
		return map;
	}

	public static void main1(String[] args) {
		try {
			new AnnotationUtil().loadVlaue("test", "com.ustctech.work.entity.YouthActivity");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
