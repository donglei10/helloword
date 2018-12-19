/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dl.springcloud.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
 

/**
 * @author Ludovic Orban
 */
public class CacheServletContextListener implements HttpSessionListener  {
	  
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		//BaseDicDao	dicDao = (BaseDicDao) getObjectFromApplication(event.getSession().getServletContext(),"baseDicDao");
		//DicDataCache.init(dicDao);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		//BaseDicDao	dicDao = (BaseDicDao) getObjectFromApplication(event.getSession().getServletContext(),"baseDicDao");
		//DicDataCache.close();
	}
	
	/** 
     * 通过WebApplicationContextUtils 得到Spring容器的实例。根据bean的名称返回bean的实例。 
     * @param servletContext  ：ServletContext上下文。 
     * @param beanName  :要取得的Spring容器中Bean的名称。 
     * @return 返回Bean的实例。 
     */  
    private Object getObjectFromApplication(ServletContext servletContext,String beanName){  
        //通过WebApplicationContextUtils 得到Spring容器的实例。  
        ApplicationContext application=WebApplicationContextUtils.getWebApplicationContext(servletContext);  
        //返回Bean的实例。  
        return application.getBean(beanName);
    } 

 
}
