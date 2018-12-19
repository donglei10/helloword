package com.dl.springcloud.common.sms;

/**
 * 
* @ClassName: SmsResult 
* @Description: TODO(短信接口调用返回对象) 
* @author xiefugui
* @date 2015-12-5 下午04:40:25 
*
 */
public class SmsResult implements java.io.Serializable{

	private static final long serialVersionUID = -4722140108648351852L;
	
	/**
	 * 错误码
	 */
	private int code;
	/**
	 * 错误描述
	 */
	private String msg;
	/**
	 * 具体错误描述或解决方法
	 */
	private String detail;
	private Result result;
	
	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public String getDetail() {
		return detail;
	}



	public void setDetail(String detail) {
		this.detail = detail;
	}



	public Result getResult() {
		return result;
	}



	public void setResult(Result result) {
		this.result = result;
	}



	@Override
	public String toString() {
		return "SmsResult [code=" + code + ", msg=" + msg + ", detail="
				+ detail + ", result=" + result + "]";
	}
	
	
	
	
}
class Result implements java.io.Serializable{
	
	private static final long serialVersionUID = 3560919074001072403L;
	
	private int count;
	private int fee;
	private String sid;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}




	public String getSid() {
		return sid;
	}




	public void setSid(String sid) {
		this.sid = sid;
	}




	@Override
	public String toString() {
		return "Result [count=" + count + ", fee=" + fee + ", sid=" + sid + "]";
	}
	
	
	
}
