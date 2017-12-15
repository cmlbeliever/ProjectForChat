package com.cml.chat.lesson.lesson6;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

@Component
public class SimpleBeanScanner implements EnvironmentAware, ResourceLoaderAware {
	private static Logger log = LoggerFactory.getLogger(SimpleBeanScanner.class);
	private ResourceLoader resourceLoader;
	private Environment environment;

	@PostConstruct
	public void scan() {
		log.info("==========================start scan==============================");
		MyClassScanner scanner = new MyClassScanner();
		scanner.setEnvironment(environment);
		scanner.setResourceLoader(resourceLoader);
		scanner.addIncludeFilter(new TypeFilter() {

			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
				return true;
			}
		});
		log.info(scanner.findCandidateComponents("com.cml.chat.lesson.extra").toString());
		log.info("==========================end scan==============================");
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
