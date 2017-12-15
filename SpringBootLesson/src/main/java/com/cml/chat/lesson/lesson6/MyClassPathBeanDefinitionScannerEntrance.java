package com.cml.chat.lesson.lesson6;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

//@Component
public class MyClassPathBeanDefinitionScannerEntrance implements BeanFactoryAware {

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

		MyClassPathBeanDefinitionScanner scanner = new MyClassPathBeanDefinitionScanner((BeanDefinitionRegistry) beanFactory);
		scanner.addIncludeFilter(new TypeFilter() {

			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				return true;
			}
		});
		System.out.println("========MyClassPathBeanDefinitionScannerEntrance==========>" + scanner.scan("com.cml.chat.lesson.extra"));

	}

}
