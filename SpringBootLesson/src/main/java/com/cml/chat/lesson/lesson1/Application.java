package com.cml.chat.lesson.lesson1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class Application {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
		MyConfig config = application.getBean(MyConfig.class);
		System.out.println(config);
		application.close();
	}
}
