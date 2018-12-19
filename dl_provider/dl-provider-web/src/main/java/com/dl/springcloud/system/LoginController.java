package com.dl.springcloud.system;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.dl.springcloud.bean.User;
//import com.dl.springcloud.demo.HelloService;


/**
 * Created by Administrator on 2017/10/29.
 */
@RestController 
@RefreshScope
public class LoginController {

	//@Resource(name="helloServiceImpl")
	//private HelloService service;
	 
	
	@RequestMapping("/login")
    public String index(Model model, HttpServletResponse response) {
        model.addAttribute("name", "simonsfan");
        return "login";
    }

}
