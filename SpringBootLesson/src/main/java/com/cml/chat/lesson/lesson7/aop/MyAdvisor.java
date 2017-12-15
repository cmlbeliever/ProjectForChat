package com.cml.chat.lesson.lesson7.aop;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

public class MyAdvisor extends AbstractBeanFactoryPointcutAdvisor {

	private Pointcut pointCut = new MyPointCut();

	@Override
	public Pointcut getPointcut() {
//		RegexpMethodPointcutAdvisor;
		return pointCut;
	}

}
