package com.dl.springcloud.common.utils;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;


/**
 * 
* @ClassName: BhGenerator 
* @Description: TODO(编号生成器) 
* @author xiefugui
* @date 2015-12-5 下午03:44:25 
*
 */
public class BhGenerator {

	/**
	 * 产生普通编号同步线程使用，由getBh()函数调用
	 */
	private static final Object synObj = new Object();
	
	/**
	 * 产生普通编号时所使用的流水号，由getBh()函数调用
	 */
	public static int serialNumber = 0;
	
	
	/**
	 * @FunName: getNumberFormat
	 * @Description : 获取指定位数为bit的数字格式器
	 * @param bit
	 *            ： 指定的位数
	 * @return NumberFormat： 返回数字格式器；
	 */
	public static NumberFormat getNumberFormat(int bit) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMinimumIntegerDigits(bit);
		formatter.setMaximumIntegerDigits(bit);
		formatter.setGroupingUsed(false);
		return formatter;
	}
	
	/**
	 * @FunName: getCurrentTimeString
	 * @Description : 获取当前时间字符串，精确到毫秒，其中年4位、月2位、日2位、时2位、分2 位、秒2位、毫秒3位
	 * @return String： 返回当前时间字符串；
	 */
	public static String getCurrentTimeString() {
		NumberFormat formatter2 = getNumberFormat(2);
		NumberFormat formatter3 = getNumberFormat(3);
		NumberFormat formatter4 = getNumberFormat(4);
		Calendar Cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
		StringBuffer sb = new StringBuffer();
		sb.append(formatter4.format(Cld.get(Calendar.YEAR)));
		sb.append(formatter2.format(Cld.get(Calendar.MONTH) + 1));
		sb.append(formatter2.format(Cld.get(Calendar.DATE)));
		sb.append(formatter2.format(Cld.get(Calendar.HOUR_OF_DAY)));
		sb.append(formatter2.format(Cld.get(Calendar.MINUTE)));
		sb.append(formatter2.format(Cld.get(Calendar.SECOND)));
		sb.append(formatter3.format(Cld.get(Calendar.MILLISECOND)));
		return sb.toString();
	}
	
	/**
	 * @FunName: getBh
	 * @Description : 
	 * @return String： 返回当前自动生成的编号；
	 */
	public static String getBh() {
		long temp;
		synchronized (synObj) {
			temp = serialNumber++;
			if (serialNumber == 1000) {// 流水号从0-999循环
				serialNumber = 0;
			}
		}
		return getCurrentTimeString() + getNumberFormat(3).format(temp);
	}
	
	/**
	 * 获取随机编码  时分秒17位+3随机
	 * @return
	 */
	public static String getRandomBh() {
		synchronized (synObj) {
			return getCurrentTimeString() + getNumberFormat(3).format(new Random().nextInt(999));
		}
	}
	

	public static void main1(String[] args) {
		/*String str="{\"code\":0,\"msg\":\"OK\",\"detail\":\"账户需要充值，请充值后重试\",\"result\":{\"count\":1,\"fee\":1,\"sid\":4022141662}}";
		Gson gson = new Gson();
		SmsResult s=gson.fromJson(str, SmsResult.class);
		System.out.println(s);*/
	}
}
