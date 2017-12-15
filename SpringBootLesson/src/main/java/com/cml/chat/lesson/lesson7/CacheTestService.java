package com.cml.chat.lesson.lesson7;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "simpleCache")
public class CacheTestService {

	@Cacheable(key = "'test'")
	public synchronized String getName() {
		System.out.println("====read====>");
		return "testName";
	}

}
