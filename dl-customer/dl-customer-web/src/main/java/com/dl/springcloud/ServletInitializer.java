package com.dl.springcloud;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Administrator on 2017/10/26.
 */
public class ServletInitializer  extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationCustomer.class);
    }
    
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(ServletInitializer.class).web(true).run(args);
//    }
}
