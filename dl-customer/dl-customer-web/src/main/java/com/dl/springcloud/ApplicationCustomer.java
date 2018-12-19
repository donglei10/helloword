package com.dl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by Administrator on 2017/9/29.
 */
@SpringCloudApplication
@EnableFeignClients
@EnableAutoConfiguration
@EnableEurekaClient
public class ApplicationCustomer   {
 
//    public static void main(String[] args) {
//        SpringApplication.run(ApplicationCustomer.class, args);
//    }
}
