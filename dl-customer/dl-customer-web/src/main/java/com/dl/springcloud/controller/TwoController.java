package com.dl.springcloud.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/9/29.
 */
@EnableAutoConfiguration
@Component
@RestController
@RefreshScope
@RequestMapping(value="/two")
public class TwoController {

    @RequestMapping("/url")
    public String url() {

        return "ok back2!";
    }


}
