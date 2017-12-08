package com.cml.chat.lesson.lesson6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class Application {
	private static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		SpringApplication springApplication = new SpringApplication(Application.class);
		ConfigurableApplicationContext application = springApplication.run(args);
	}
}
