package com.dl.springcloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Administrator on 2017/9/29.
 */
@EnableEurekaServer
@SpringBootApplication
public class ApplicationEureka {



    public static void main(String[] args) {
    	 
         SpringApplication.run(ApplicationEureka.class, args); 
    	 
    }

}
