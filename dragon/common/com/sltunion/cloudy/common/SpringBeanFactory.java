package com.sltunion.cloudy.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringBeanFactory {

	public static Object getSpringBean(String beanName) {
		WebApplicationContext wac = ContextLoader
				.getCurrentWebApplicationContext();
		if(wac == null){
			ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-resources.xml");
			return ctx.getBean(beanName);
		}else{
		}
		return wac.getBean(beanName);
	}
}