package com.cml.chat.lesson.lesson7.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class MyPointCutConfiguration {

	@Bean
	public MyAdvisor myAdvisor() {
		System.out.println("==========================MyPointCutConfiguration start==================================");
		MyAdvisor advisor = new MyAdvisor();
		advisor.setAdvice(myAdvice());
		advisor.setOrder(Ordered.HIGHEST_PRECEDENCE);
		System.out.println("==========================MyPointCutConfiguration end==================================");
		return advisor;
	}

	@Bean
	public MyAdvice myAdvice() {
		return new MyAdvice();
	}
}
