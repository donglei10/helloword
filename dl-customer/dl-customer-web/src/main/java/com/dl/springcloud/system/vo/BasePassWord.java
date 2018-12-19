package com.dl.springcloud.system.vo;

public class BasePassWord {

	private String phone ;
	
	private String userid;
	
	private String verificationCode ;
	
	private boolean issafe;
	
	public BasePassWord(){
		
	}
	
	public BasePassWord(String phone, String userid, String verificationCode) {
		super();
		this.phone = phone;
		this.userid = userid;
		this.verificationCode = verificationCode;
	}



	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public boolean isIssafe() {
		return issafe;
	}
	public void setIssafe(boolean issafe) {
		this.issafe = issafe;
	}
	
	
	
}
