package com.dl.springcloud.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/10/29.
 */
@FeignClient(name= "provider")
public interface HelloRemoteService {
	
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}
