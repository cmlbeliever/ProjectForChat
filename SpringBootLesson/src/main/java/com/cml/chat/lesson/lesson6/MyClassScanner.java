package com.cml.chat.lesson.lesson6;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;

public class MyClassScanner extends ClassPathScanningCandidateComponentProvider {

	public MyClassScanner() {
	}

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		AnnotationMetadata metadata = beanDefinition.getMetadata();
		return (metadata.isIndependent() && (metadata.isAbstract() || metadata.isInterface()));
	}
}
