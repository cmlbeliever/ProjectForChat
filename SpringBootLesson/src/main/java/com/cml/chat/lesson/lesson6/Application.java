package com.cml.chat.lesson.lesson6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import com.cml.chat.lesson.extra.ExtraInterface;

@Import(MyClassPathBeanDefinitionScannerEntrance2.class)
@SpringBootApplication()
public class Application {
	private static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		SpringApplication springApplication = new SpringApplication(Application.class);
		ConfigurableApplicationContext application = springApplication.run(args);

		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) application.getBeanFactory();

		System.out.println("result==>" + beanFactory.getBean(ExtraInterface.class));

		// PathMatchingResourcePatternResolver resolver = new
		// PathMatchingResourcePatternResolver();
		// Resource[] resources =
		// resolver.getResources("classpath:/com/cml/chat/lesson/extra/**.class");
		//
		// log.info("findResouceSize==>" + Arrays.asList(resources).toString());
	}
}
