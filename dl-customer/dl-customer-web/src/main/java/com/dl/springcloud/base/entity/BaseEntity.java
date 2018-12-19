package com.dl.springcloud.base.entity;

import java.io.Serializable;
import java.util.Date;

 
public abstract class BaseEntity implements Serializable{
	
	@SuppressWarnings("unused")
	protected static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	protected Integer id;
	
	/**
	 * 创建时间
	 */
	protected Date createTime;
	/***
	 * 状态
	 */
	protected String state;
	
	/**
	 * 审核结果
	 */
	@TempType
	private String result;
	/**
	 * 审核说明
	 */
	@TempType
	private String resultRemark;
	
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the resultRemark
	 */
	public String getResultRemark() {
		return resultRemark;
	}
	/**
	 * @param resultRemark the resultRemark to set
	 */
	public void setResultRemark(String resultRemark) {
		this.resultRemark = resultRemark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
