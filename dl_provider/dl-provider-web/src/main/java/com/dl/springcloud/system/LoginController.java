package com.dl.springcloud.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.dl.springcloud.bean.User;
//import com.dl.springcloud.demo.HelloService;


/**
 * Created by Administrator on 2017/10/29.
 */
//@RestController 
//@RefreshScope
@Controller 
public class LoginController {

	//@Resource(name="helloServiceImpl")
	//private HelloService service;
	 
	
	@RequestMapping("/")
    public String index(Model model, HttpServletResponse response) {
        
        return "login";
    }
	
	@RequestMapping("/login")
	public String dologin(Model model,HttpServletRequest request,String username,String password){
		   HttpSession session = request.getSession();
	        String sessionUsername = (String) session.getAttribute("username");
	        if (StringUtils.isEmpty(sessionUsername)) {
	            session.setAttribute("username", sessionUsername);
	        }
	        System.out.println("用户：" + username);
	        System.out.println("访问端口：" + request.getServerPort());
	        return "/index";
	}

}
