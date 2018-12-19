package com.dl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Administrator on 2017/9/29.
 */
@SpringCloudApplication
@EnableZuulProxy
@EnableConfigServer
public class ApplicationConfigServer {



    public static void main(String[] args) {
    	
         SpringApplication.run(ApplicationConfigServer.class, args); 
    	 
    }

}
