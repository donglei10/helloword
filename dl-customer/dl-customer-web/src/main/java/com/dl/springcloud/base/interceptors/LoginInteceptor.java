package com.dl.springcloud.base.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dl.springcloud.system.entity.BaseUser;



@Component("loginInteceptor")
public class LoginInteceptor  extends HandlerInterceptorAdapter  {
	 /** 
     * 最后执行，可用于释放资源 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
        super.afterCompletion(request, response, handler, ex);  
    }  
    
    /** 
     * 显示视图前执行 
     */  
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
        System.out.println(request.getContentType()+"-----"+request.getCharacterEncoding()+"------"+request.getContextPath());  
        super.postHandle(request, response, handler, modelAndView);  
    }  
    
    private static final String[] IGNORE_URI = {"/login.jsp", "/relogin", "/login",
    		"/security","/doaction/login","/doaction/user","/static","/fileUpDownController/download","/work/activityController/shareactivity",
    		"sys/password/",
    		"sys/register/"
    	};
    /** 
     * Controller之前执行 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
         String url = request.getRequestURI();  
         boolean flag =false;
         for (String s : IGNORE_URI) {
             if (url.contains(s)) {
                 flag = true;
                 break;
             }
         }
         if (!flag) {
        	 String uuid = request.getHeader("uuid");
        	 String imei = request.getHeader("imei");
        	 String idcard = request.getHeader("idcard");
        	 String userid = request.getHeader("userid");
        	 HttpSession session =  request.getSession();
        	 Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());  
             response.addCookie(cookie);  
        	 if (null == session) {
        		 response.sendRedirect(request.getContextPath()+"/relogin");
                 return false;
             }
             BaseUser user = null;
             if(StringUtils.isNotBlank(uuid)){
            	 user = (BaseUser) session.getAttribute("UUID_LOGIN_USER");
             }else if(StringUtils.isNotBlank(imei)){
            	// user = (BaseUser) session.getAttribute("IMEI_LOGIN_USER");
            	 user = new BaseUser();
             }else if(StringUtils.isNotBlank(idcard)){
            	// user = (BaseUser) session.getAttribute("CARD_LOGIN_USER");
            	 user = new BaseUser();
             }else{
            	 user = (BaseUser) session.getAttribute("WEB_LOGIN_USER");
             }
        	 if(user==null && StringUtils.isBlank(userid)){
        		 if(StringUtils.isNotBlank(uuid)){
        			 response.sendRedirect("relogin");
                 }else if(StringUtils.isNotBlank(imei)){
                	 response.sendRedirect("relogin");
                 }else if(StringUtils.isNotBlank(idcard)){
                	 response.sendRedirect("relogin");
                 }else{
                	 response.sendRedirect(request.getContextPath()+"/relogin");
                 }
    		     return false;
             }
         }
        return  super.preHandle(request, response, handler);  
    }  

}
