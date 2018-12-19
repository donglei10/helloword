package com.dl.springcloud.system.dao;

import java.util.List;

 

import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.dl.springcloud.system.model.Dictionary;

@Repository
public interface IDictionaryDao extends JpaRepository<Dictionary, Integer>,JpaSpecificationExecutor<Dictionary>  {
 
    
	@Select("select * from t_dic where id=#{id}")//mybatis的注解  
	Dictionary findById(String userName);

 
	@Select("select * from t_dic ")//mybatis的注解 
	List<Dictionary> findAll();
 
	
}
