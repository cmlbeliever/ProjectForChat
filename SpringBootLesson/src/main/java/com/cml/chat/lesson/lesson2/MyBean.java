package com.cml.chat.lesson.lesson2;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements IMyBean, InitializingBean {
	private Logger log = LoggerFactory.getLogger(MyBean.class);

	private String customValue = "myCustomValue";

	public String getCustomValue() {
		return customValue;
	}

	public void setCustomValue(String customValue) {
		this.customValue = customValue;
	}

	@PostConstruct
	public void postConstruct() {
		log.info("=======>postConstruct");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("=======>afterPropertiesSet");
	}

}
