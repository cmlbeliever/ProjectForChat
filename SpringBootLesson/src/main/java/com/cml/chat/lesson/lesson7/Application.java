package com.cml.chat.lesson.lesson7;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@Import(AutoProxyRegistrar.class)
@SpringBootApplication()
public class Application implements EnvironmentAware {
	private static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.setWebEnvironment(false);
		ConfigurableApplicationContext application = springApplication.run(args);
		CacheTestService cacheService = application.getBean(CacheTestService.class);

		System.out.println(cacheService.getName());

		CacheManager cacheManager = application.getBean(CacheManager.class);
		ConcurrentMapCache cahce = (ConcurrentMapCache) cacheManager.getCache("simpleCache");
		System.out.println(cahce.getNativeCache());

		// application.close();
	}

	@Bean
	public CacheManager cacheManager() {

		Cache cache = new ConcurrentMapCache("simpleCache");
		SimpleCacheManager manager = new SimpleCacheManager();
		manager.setCaches(Arrays.asList(cache));

		return manager;
	}

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("===========>" + environment.getProperty("myconfig.name"));
	}

}
