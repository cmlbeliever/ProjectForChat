package com.cml.chat.lesson.lesson2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	private Logger log = LoggerFactory.getLogger(MyBeanPostProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof IMyBean) {
			log.info("=======>postProcessAfterInitialization");
			IMyBean mybean = (IMyBean) bean;
			if (mybean.getCustomValue() == null) {
				mybean.setCustomValue("defaultValue");
			}
		}
		return bean;
	}

}
