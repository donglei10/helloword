package com.dl.springcloud;
 
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * @SpringCloudApplication
 * Created by Administrator on 2017/10/29.
 */

@SpringCloudApplication
@EnableZuulProxy
@EnableEurekaClient
public class ApplicationProvider {
	
    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider.class, args);
    }
}
