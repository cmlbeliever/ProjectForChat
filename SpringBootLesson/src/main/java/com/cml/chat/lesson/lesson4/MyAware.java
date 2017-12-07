package com.cml.chat.lesson.lesson4;

import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

public interface MyAware extends Aware {
	void setAware(ApplicationContext applicationContext, BeanFactory beanFactory);
}
