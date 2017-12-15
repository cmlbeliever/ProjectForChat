package com.cml.chat.lesson.lesson7.aop;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import com.cml.chat.lesson.lesson7.CacheTestService;
import com.cml.chat.lesson.lesson7.controller.HelloWorldController;

public class MyPointCut extends StaticMethodMatcherPointcut implements Serializable {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
//		System.out.println(
//				"=======MyPointcut========>" + targetClass.getName() + "," + targetClass.getName().equals(HelloWorldController.class.getName()));
		return targetClass.getName().equals(CacheTestService.class.getName());
	}

}
