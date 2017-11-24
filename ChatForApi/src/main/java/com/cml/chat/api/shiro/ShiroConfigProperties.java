package com.cml.chat.api.shiro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:shiro/shiro.properties")
@ConfigurationProperties(prefix = "shiro")
public class ShiroConfigProperties {
	private String loginUrl;
	private String loginSuccess;
	private String unauthorized;
	private LinkedHashMap<String, String> filters = new LinkedHashMap<>();
	private String definitions;

	public String getDefinitions() {
		return definitions;
	}

	public void setDefinitions(String definitions) {
		this.definitions = definitions;
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			InputStreamReader reader = new InputStreamReader(resolver.getResource(definitions).getInputStream());
			BufferedReader bufReader = new BufferedReader(reader);
			String line = null;
			while (null != (line = bufReader.readLine())) {
				if (StringUtils.isBlank(line)) {
					continue;
				}
				int index = line.indexOf("=");
				if (index != -1) {
					String key = line.substring(0, index);
					String value = line.substring(index + 1);
					filters.put(key, value);
				}
			}
			bufReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public LinkedHashMap<String, String> getFilters() {
		return filters;
	}

	public void setFilters(LinkedHashMap<String, String> filters) {
		this.filters = filters;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(String loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public String getUnauthorized() {
		return unauthorized;
	}

	public void setUnauthorized(String unauthorized) {
		this.unauthorized = unauthorized;
	}

}
