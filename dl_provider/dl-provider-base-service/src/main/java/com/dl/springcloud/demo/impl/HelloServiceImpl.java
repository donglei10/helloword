package com.dl.springcloud.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dl.springcloud.base.service.impl.BaseServiceImpl;
import com.dl.springcloud.bean.User;
import com.dl.springcloud.dao.IUserDao;
import com.dl.springcloud.demo.HelloService;
 

@Service
public class HelloServiceImpl extends BaseServiceImpl<User, Integer> implements HelloService {

	@Autowired
    private IUserDao userDao;  
	
	public User findUser(int userId){
		return userDao.findOne(userId);
	}
	
	public User createUser(){
		User u = new User();
		u.setUserId("u-02");
		u.setUserName("u_name");
		userDao.save(u);
		return u;
	}
}
