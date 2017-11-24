package com.cml.learn.starter.framework;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import com.cml.learn.starter.framework.factorybean.MyLogFactoryBean;
import com.cml.learn.starter.framework.scanner.MyLogClasspathScanner;

public class MyLogAutofigurationRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

	private static Logger log = LoggerFactory.getLogger(MyLogAutofigurationRegistrar.class);
	private static final String FACTORY_BEAN_KEY = "mylog.config.factorybean";
	private static final String BASE_PACKAGE_KEY = "mylog.config.basepackage";
	private Environment env;
	private ResourceLoader resourceLoader;

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
		if (log.isDebugEnabled()) {
			log.debug("setEnvironment");
		}
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		if (log.isDebugEnabled()) {
			log.debug("setResourceLoader");
		}
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		if (log.isDebugEnabled()) {
			log.debug("registerBeanDefinitions");
		}

		// 获取自动log配置的basePackage
		AnnotationAttributes annoAttrs = AnnotationAttributes
				.fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyLogAutoConfiguration.class.getName()));

		// 获取扫描的basepackage和接口实现类
		String basePackage = retrieveBasePackage(annoAttrs, importingClassMetadata.getClassName());
		Class<? extends AbstractFactoryBean> implClass = retrieveFactoryBean(annoAttrs);

		// 扫描基础包下所有带有MyLogScanner注解的接口
		MyLogClasspathScanner scanner = new MyLogClasspathScanner(registry);
		scanner.setResourceLoader(resourceLoader);
		scanner.addIncludeFilter(new AnnotationTypeFilter(MyLogScanner.class));
		// exclude package-info.java
		scanner.addExcludeFilter(new TypeFilter() {
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				String className = metadataReader.getClassMetadata().getClassName();
				return className.endsWith("package-info");
			}
		});
		scanner.setFactoryBeanImplClass(implClass);
		scanner.doScan(basePackage);
	}

	/**
	 * 获取对应的接口实现类
	 * 
	 * @param annoAttrs
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Class<? extends AbstractFactoryBean> retrieveFactoryBean(AnnotationAttributes annoAttrs) {
		// 使用Enable方式注入
		if (null != annoAttrs) {
			return annoAttrs.getClass("implClass");
		}
		// 使用jar导入的方式注入
		try {
			// 通过属性自定义配置
			String factoryBeanName = env.getProperty(FACTORY_BEAN_KEY);
			if (null != factoryBeanName) {
				return (Class<? extends AbstractFactoryBean>) Class.forName(factoryBeanName);
			}
			return (Class<? extends AbstractFactoryBean>) Class.forName(MyLogFactoryBean.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		throw new IllegalArgumentException("myLogFactoryBean implClass isRequired!!!");

	}

	/**
	 * 获取扫描基础包，没有配置的话默认是整个工程
	 * 
	 * @param annoAttrs
	 * @return
	 */
	private String retrieveBasePackage(AnnotationAttributes annoAttrs, String bootClassName) {

		// 使用Enable方式注入
		if (null != annoAttrs) {
			return annoAttrs.getString("basePackage");
		}

		String configBasePackage = env.getProperty(BASE_PACKAGE_KEY);

		if (null != configBasePackage) {
			return configBasePackage;
		}
		try {
			return Class.forName(bootClassName).getPackage().getName();
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("basePackage is Required!!!");
		}
	}

}
