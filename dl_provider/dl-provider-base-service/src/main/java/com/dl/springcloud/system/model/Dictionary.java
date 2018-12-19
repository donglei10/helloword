package com.dl.springcloud.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_dic")
public class Dictionary {
	@Id
	private Integer id;
	
	private String tCode;
	
	private String tText;
	
	private String tType;
	
	private String tState;
	
	@Column(name="t_remark")
	private String tRemark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String gettCode() {
		return tCode;
	}

	public void settCode(String tCode) {
		this.tCode = tCode;
	}

	public String gettText() {
		return tText;
	}

	public void settText(String tText) {
		this.tText = tText;
	}

	public String gettType() {
		return tType;
	}

	public void settType(String tType) {
		this.tType = tType;
	}

	public String gettState() {
		return tState;
	}

	public void settState(String tState) {
		this.tState = tState;
	}

	public String gettRemark() {
		return tRemark;
	}

	public void settRemark(String tRemark) {
		this.tRemark = tRemark;
	}
	
		
	
}
