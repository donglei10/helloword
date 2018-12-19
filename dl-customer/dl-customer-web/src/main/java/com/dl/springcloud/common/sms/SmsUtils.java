package com.dl.springcloud.common.sms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dl.springcloud.common.utils.StringUtils;



/**
 * 短信工具类
 * @author xiefugui
 * @date 2015-12-05
 *
 */
public class SmsUtils {

	private static Logger log=Logger.getLogger(SmsUtils.class);
	/**
	 * 短信类型
	 */
	public static String SMS_TYPE="smstype";
	/**
	 * 短信号码，多个号码以 "," 分割
	 */
	public static String SMS_PHONE="smsphone";
	/**
	 * 短信内容
	 */
	public static String SMS_CONTENT="smscotent";
	/**
	 * 发送短信时间，不存在为当前时间
	 */
	public static String SMS_TIME="smstime";
	
	/**
	 * sms 对应 code 解释
	 */
	public static Map<Integer,String> smscodeMap=new HashMap<Integer, String>();
	
	/**
	 *  短信发送
	 * @param sendMap
	 * 			key:SmsUtils.SMS_PHONE,SmsUtils.SmsUtils.SMS_PHONE must be exist
	 * @return
	 */
	public static SmsResult sendSms(Map<String,String> sendMap){
		SmsResult sms=null;
		if(sendMap==null || sendMap.isEmpty()) return sms;
		String text=sendMap.get(SMS_CONTENT);
		String mobile=sendMap.get(SMS_CONTENT);
		try {
			sms=JavaSmsApi.sendSms(text, mobile);
			log.debug("send sms success...");
		} catch (IOException e) {
			log.error("send sms exception ...", e);
		}
		return sms;
	}
	
	/**
	 * 发送短信
	 * @param content
	 * 				短信内容
	 * @param phone
	 * 				发送手机号码
	 * @return
	 */
	public static SmsResult sendSms(String content,String phone){
		SmsResult sms=null;
		if("".equals(StringUtils.null2String(phone)) || "".equals(StringUtils.null2String(content))){
			log.debug("content or phone is null...");
			return sms;
		}
		try {
			sms=JavaSmsApi.sendSms(content, phone);
			log.debug("send sms success...");
		} catch (IOException e) {
			log.error("send sms exception ...", e);
		}
		return sms;
	}
	
	
	
	
	static{
		smscodeMap.put(0, "调用成功");
		smscodeMap.put(1, "补充必须传入的参数");
		smscodeMap.put(2, "按提示修改参数值的格式");
		smscodeMap.put(3, "账户需要充值，请充值后重试");
		smscodeMap.put(4, "关键词屏蔽，修改关键词后重试");
		smscodeMap.put(5, "模板id不存在或者已经删除");
		smscodeMap.put(6, "模板有一定的规范，按失败提示修改");
		smscodeMap.put(7, "审核状态的模板和审核未通过的模板不可用");
		smscodeMap.put(8, "请检查是否同一手机号在30秒内重复提交相同的内容");
		smscodeMap.put(9, "为避免重复发送骚扰用户，同一手机号5分钟内相同内容最多允许发3次");
		smscodeMap.put(10, "手机号在黑名单列表中（你可以把不想发送的手机号添加到黑名单列表）");
		smscodeMap.put(11, "接口不支持GET方式调用，请按提示或者文档说明的方法调用，一般为POST");
		smscodeMap.put(12, "接口不支持POST方式调用，请按提示或者文档说明的方法调用，一般为GET");
		smscodeMap.put(13, "由于运营商管制，营销短信暂时不能发送");
		smscodeMap.put(14, "请确认内容编码是否设置正确");
		smscodeMap.put(15, "短信签名与预设的固定签名不匹配");
		smscodeMap.put(16, "短信内容不能包含多个签名【 】符号");
		smscodeMap.put(17, "请检查程序是否有异常或者系统是否被恶意攻击");
		smscodeMap.put(18, "签名校验失败");
		smscodeMap.put(19, "请求已失效");
		smscodeMap.put(20, "不支持的国家地区");
		smscodeMap.put(21, "解密失败");
		smscodeMap.put(22, "1小时内同一手机号发送次数超过限制");
		smscodeMap.put(23, "发往模板支持的国家列表之外的地区");
		smscodeMap.put(24, "添加告警设置失败");
		smscodeMap.put(25, "手机号和内容个数不匹配");
		smscodeMap.put(26, "流量包错误");
		smscodeMap.put(27, "未开通金额计费");
		smscodeMap.put(28, "运营商错误");
		smscodeMap.put(-1, "apikey不正确或没有授权");
		smscodeMap.put(-2, "用户没有对应的API权限");
		smscodeMap.put(-3, "访问IP不在白名单之内，可在后台\"账户设置->IP白名单设置\"里添加该IP");
		smscodeMap.put(-4, "调整访问频率或者申请更高的调用量");
		smscodeMap.put(-5, "短期内访问过于频繁，请降低访问频率");
		smscodeMap.put(-50, "系统出现未知的异常情况");
		smscodeMap.put(-51, "系统繁忙，请稍后重试");
		smscodeMap.put(-52, "充值时系统出错");
		smscodeMap.put(-53, "提交短信时系统出错");
		smscodeMap.put(-54, "常见于插入键值已存在的记录");
		smscodeMap.put(-55, "没有找到预期中的数据");
		smscodeMap.put(-57, "联系客服或技术支持设置固定签名");
	}
	
	
}
