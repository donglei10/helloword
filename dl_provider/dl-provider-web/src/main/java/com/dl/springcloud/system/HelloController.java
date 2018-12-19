package com.dl.springcloud.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.dl.springcloud.bean.User;
//import com.dl.springcloud.demo.HelloService;


/**
 * Created by Administrator on 2017/10/29.
 */
@RestController 
@RefreshScope
public class HelloController {

	//@Resource(name="helloServiceImpl")
	//private HelloService service;
	 
	//@Value(value = "${a}")
	private String a;
    @RequestMapping("/hello")
    @ResponseBody
    public String index(@RequestParam String name) {
    	//User u = service.createUser();
    	 
    	//System.out.println("uid>>>2"+u.getId());
    	//System.out.println("userid>>>"+u.getUserId());
    	//System.out.println("username>>>"+u.getUserName());

        return "hello "+name+a+", this is first messge";
    }

}
