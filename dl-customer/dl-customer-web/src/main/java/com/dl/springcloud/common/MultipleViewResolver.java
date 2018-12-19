package com.dl.springcloud.common;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 视图转发工具
 * @author donglei
 *
 */
public class MultipleViewResolver  implements ViewResolver {

	private Map<String, ViewResolver> resolvers;    
	/*
	 *  视图转发，，默认转发到 vm 模板
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.ViewResolver#resolveViewName(java.lang.String, java.util.Locale)
	 */
	@Override
	public View resolveViewName(String view, Locale locale) throws Exception {
		int n = view.lastIndexOf(".");  
		  String suffix = "";
        if(n== -1 ){
        	suffix ="jsp";  
        }else{
        	 suffix = view.substring(n+1);  
        	 view = view.substring(0, n);
        }  
        
        ViewResolver resolver = resolvers.get(suffix);    
        if(resolver!=null)    
            return resolver.resolveViewName(view, locale);   
        return null;  
	}

	public Map<String, ViewResolver> getResolvers() {
		return resolvers;
	}

	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}

	
}
