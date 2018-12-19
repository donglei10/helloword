package com.dl.springcloud.common.sms;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 短信http接口的java代码调用示例
* 基于Apache HttpClient 4.3
*
* @author xiefugui
* @since 2015-04-03
*/

public class JavaSmsApi {
@SuppressWarnings("unused")
private static Logger log=Logger.getLogger(JavaSmsApi.class);

//查账户信息的http地址
private static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";

//智能匹配模版发送接口的http地址
private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

//模板发送接口的http地址
private static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";

//发送语音验证码接口的http地址
private static String URI_SEND_VOICE = "http://yunpian.com/v1/voice/send.json";

//编码格式。发送编码格式统一用UTF-8
private static String ENCODING = "UTF-8";

//您的apikey
private static String APIKEY = "b59026a0ff274499ed00808f818cdaa8";

//短信号码
private static String SMS_MOBILE = "mobile";

//短信内容
private static String SMS_TEXT = "text";

//短信验证apikey
private static String SMS_APIKEY = "apikey";


public static void main1(String[] args) throws IOException, URISyntaxException {

    //修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后用户中心首页看到
    //String apikey = "64a12dd94d887a212bc15eae51fb48ad";

    //修改为您要发送的手机号
    String mobile = "13965136805";

    /**************** 查账户信息调用示例 *****************/
    System.out.println(JavaSmsApi.getUserInfo(APIKEY));

    /**************** 使用智能匹配模版接口发短信(推荐) *****************/
    //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
    String text = "【丁点科技】您的验证码：1234";
    //发短信调用示例
    System.out.println(JavaSmsApi.sendSms(APIKEY, text, mobile));

    /**************** 使用指定模板接口发短信(不推荐，建议使用智能匹配模版接口) *****************/
    //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
    long tpl_id = 1;
    //设置对应的模板变量值
    //如果变量名或者变量值中带有#&=%中的任意一个特殊符号，需要先分别进行urlencode编码
    //如code值是#1234#,需作如下编码转换
    String codeValue = URLEncoder.encode("#1234#", ENCODING);
    String tpl_value = "#code#=" + codeValue + "&#company#=云片网";
    //模板发送的调用示例
    //System.out.println(JavaSmsApi.tplSendSms(apikey, tpl_id, tpl_value, mobile));

    /**************** 使用接口发语音验证码 *****************/
    //String code = "1234";
    //System.out.println(JavaSmsApi.sendVoice(apikey, mobile ,code));
}

/**
* 取账户信息
*
* @return json格式字符串
* @throws java.io.IOException
*/

public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
    Map<String, String> params = new HashMap<String, String>();
    params.put(SMS_APIKEY, apikey);
    return post(URI_GET_USER_INFO, params);
}

/**
* 智能匹配模版接口发短信
*
* @param apikey apikey
* @param text   　短信内容
* @param mobile 　接受的手机号
* @return json格式字符串
* @throws IOException
*/
public static String sendSms(String apikey, String text, String mobile) throws IOException {
    Map<String, String> params = new HashMap<String, String>();
    params.put(SMS_APIKEY, apikey);
    params.put(SMS_TEXT, text);
    params.put(SMS_MOBILE, mobile);
    return post(URI_SEND_SMS, params);
}

/**
* 智能匹配模版接口发短信  使用默认的apikey
*
* @param text   　短信内容
* @param mobile 　接受的手机号
* @return SmsResult对象
* @throws IOException
*/
public static SmsResult sendSms(String text, String mobile) throws IOException {
    Map<String, String> params = new HashMap<String, String>();
    params.put(SMS_APIKEY, APIKEY);
    params.put(SMS_TEXT, text);
    params.put(SMS_MOBILE, mobile);
    String result= post(URI_SEND_SMS, params);
    Gson gson = new Gson();
	SmsResult s=gson.fromJson(result, SmsResult.class);
	return s;
}

/**
* 通过模板发送短信(不推荐)
*
* @param apikey    apikey
* @param tpl_id    　模板id
* @param tpl_value 　模板变量值
* @param mobile    　接受的手机号
* @return json格式字符串
* @throws IOException
*/

public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("apikey", apikey);
    params.put("tpl_id", String.valueOf(tpl_id));
    params.put("tpl_value", tpl_value);
    params.put("mobile", mobile);
    return post(URI_TPL_SEND_SMS, params);
}

/**
* 通过接口发送语音验证码
* @param apikey apikey
* @param mobile 接收的手机号
* @param code   验证码
* @return
*/

public static String sendVoice(String apikey, String mobile, String code) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("apikey", apikey);
    params.put("mobile", mobile);
    params.put("code", code);
    return post(URI_SEND_VOICE, params);
}

/**
* 基于HttpClient 4.3的通用POST方法
*
* @param url       提交的URL
* @param paramsMap 提交<参数，值>Map
* @return 提交响应
*/

public static String post(String url, Map<String, String> paramsMap) {
    CloseableHttpClient client = HttpClients.createDefault();
    String responseText = "";
    CloseableHttpResponse response = null;
    try {
        HttpPost method = new HttpPost(url);
        if (paramsMap != null) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                paramList.add(pair);
            }
            method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
        }
        response = client.execute(method);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            responseText = EntityUtils.toString(entity);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        return responseText;
    }
}