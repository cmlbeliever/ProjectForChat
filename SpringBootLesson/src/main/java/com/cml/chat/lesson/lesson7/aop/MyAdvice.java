package com.cml.chat.lesson.lesson7.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("=======================>>>" + invocation.getMethod().getName());
		return invocation.proceed() + "modifyd Value!!!";
	}

}
