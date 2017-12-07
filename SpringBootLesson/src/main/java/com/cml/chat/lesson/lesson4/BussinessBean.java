package com.cml.chat.lesson.lesson4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

public class BussinessBean implements ApplicationContextAware, BeanFactoryAware {
	private static Logger log = LoggerFactory.getLogger(BussinessBean.class);

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("==>setApplicationContext");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		log.info("==>setBeanFactory");
	}

}
