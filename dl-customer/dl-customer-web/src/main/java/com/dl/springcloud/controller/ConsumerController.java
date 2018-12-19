package com.dl.springcloud.controller;

import com.dl.springcloud.service.HelloRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/29.
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloRemoteService HelloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
    	System.out.println("测试路由！");
        return HelloRemote.hello(name);
    }
}
