package com.dl.springcloud.common.utils;

/**
 * 
* @ClassName: StringUtils 
* @Description: TODO(字符串工具类) 
* @author xiefugui
* @date 2015-12-5 下午07:55:19 
*
 */
public class StringUtils {

	/**
	 * @param str
	 * @return
	 * 		  返回安全字符串并且去除空格
	 */
	public static String null2String(String str){
		return str==null || "".equals(str) ? "":str.trim();
	}
	
	/**
	 * object 转 String
	 * @param obj
	 * @return
	 */
	public static String object2String(Object obj){
		return obj==null ? "" : obj.toString();
	}
}
