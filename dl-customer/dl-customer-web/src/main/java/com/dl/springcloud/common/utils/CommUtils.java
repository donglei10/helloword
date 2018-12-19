package com.dl.springcloud.common.utils;

import java.util.Random;

import com.dl.springcloud.common.sms.SmsResult;
import com.dl.springcloud.common.sms.SmsUtils;
import com.google.gson.Gson;

/**
 * 
* @ClassName: CommUtils 
* @Description: TODO(公共方法类) 
* @author xiefugui
* @date 2015-12-12 下午08:28:21 
*
 */
public class CommUtils {

	
	/**
	 * 产生随机码
	 * @param min
	 * @param max
	 * @return
	 */
	public static int nextInt(int min, int max){
		Random rand= new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}
	
	public static String getSmsContent(String phone,String username,String code){
		String message = "【丁点科技】亲爱的"+username+"，您的验证码是"+code+"。如非本人操作，请忽略本短信"; 
		SmsResult result=SmsUtils.sendSms(message, phone);
		return new Gson().toJson(result);
	}
}
