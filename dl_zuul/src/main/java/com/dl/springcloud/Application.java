package com.dl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Administrator on 2017/9/29.
 */
@SpringCloudApplication
@EnableAutoConfiguration
@EnableZuulProxy
@EnableEurekaClient
public class Application {



    public static void main(String[] args) {
    	
         SpringApplication.run(Application.class, args); 
    	 
    }

}
