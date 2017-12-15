package com.cml.chat.lesson.lesson6;

import java.io.IOException;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyClassPathBeanDefinitionScannerEntrance2 implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

		MyClassPathBeanDefinitionScanner scanner = new MyClassPathBeanDefinitionScanner(registry);
		scanner.addIncludeFilter(new TypeFilter() {

			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				return true;
			}
		});
		System.out.println("========MyClassPathBeanDefinitionScannerEntrance2==========>" + scanner.scan("com.cml.chat.lesson.extra"));

	}

}
