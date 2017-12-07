package com.cml.chat.lesson.lesson4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyAwareTest implements MyAware {
	private static Logger log = LoggerFactory.getLogger(MyAwareTest.class);

	@Override
	public void setAware(ApplicationContext applicationContext, BeanFactory beanFactory) {
		log.info("MyAwareTest.setAware===>applicationContext:" + applicationContext.getClass().getSimpleName() + ",beanFactory:"
				+ beanFactory.getClass().getSimpleName());
	}

}
