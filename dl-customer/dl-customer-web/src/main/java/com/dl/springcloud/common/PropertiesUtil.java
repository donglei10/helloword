package com.dl.springcloud.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
/**
 * 读取properties属性数据失败
 * @author donglei
 *
 */
public class PropertiesUtil {
	public static Logger logger = Logger.getLogger(PropertiesUtil.class);
	/**
	 * 根据properties属性读取数据
	 * @param name properties 的key
	 * @return
	 */
	public static String getProperty(String name){
		String result = "";
		Resource resource = new ClassPathResource("/application.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			result = props.getProperty(name);
		} catch (IOException e) {
			System.out.println("读取properties属性数据失败！");
			result = "";
		}
		return result;
	}
}
