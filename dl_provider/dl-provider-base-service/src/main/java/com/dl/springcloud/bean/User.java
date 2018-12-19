package com.dl.springcloud.bean;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Table(name = "t_user")
@Entity
public class User implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
    @TableGenerator(name="ID_GENERATOR",  //生成器名称
                    table="ID_GENERATOR", //生成器使用的表
                    pkColumnName="PK_NAME", //表中对应的字段名
                    pkColumnValue="ORDER_ID", //上述字段的值
                    valueColumnName="PK_VALUE", //值 
                    //根据上述三个属性，就可以定位到表中的PK_VALUE的值，如：1，10，100
                    allocationSize=10)//表示主键一次增加10
    @GeneratedValue(strategy=GenerationType.TABLE, generator="ID_GENERATOR")//这里的生成器和上面的生成器名称对应
    private Integer id;
	
	private String userName;
	
	private String userId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 主键策略
	 * @return
	 */
	protected String getIdkey() {
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
}
