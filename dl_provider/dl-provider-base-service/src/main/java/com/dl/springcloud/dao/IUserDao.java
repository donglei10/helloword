package com.dl.springcloud.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dl.springcloud.bean.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User>  {
	
	@Select("select * from t_user where id=#{id}")//mybatis的注解  
	User findById(String userName);
	
	
}
