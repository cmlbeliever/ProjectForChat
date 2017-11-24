package com.cml.learn.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cml.learn.starter.framework.EnableMyLogAutoConfiguration;
import com.cml.learn.starter.framework.factorybean.MyLogFactoryBeanInCglib;

//开启mylog 功能，这里可以配置使用JDK还是cglib
@EnableMyLogAutoConfiguration(basePackage = "com.cml.learn.starter.service",implClass=MyLogFactoryBeanInCglib.class)
@SpringBootApplication
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
