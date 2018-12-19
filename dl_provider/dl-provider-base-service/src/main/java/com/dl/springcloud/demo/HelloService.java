package com.dl.springcloud.demo;

import com.dl.springcloud.bean.User;

public interface HelloService {
	
	public User findUser(int userId);	
	
	public User createUser();
	
}
