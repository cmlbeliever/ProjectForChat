package com.cml.learn.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//不需要配置注解了，会自动配置进来的，详情见src/main/resource/META-INF/spring.factories
//@EnableMyLogAutoConfiguration(basePackage = "com.cml.learn.starter.service")
@SpringBootApplication
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
