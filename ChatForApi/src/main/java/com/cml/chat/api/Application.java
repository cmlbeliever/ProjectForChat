package com.cml.chat.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cml.chat.api.constant.ApiConst;
import com.cml.chat.api.model.resp.UnAuthenticationResp;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@SpringBootApplication()
@Controller
@RequestMapping
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * 未授权返回数据
	 * 
	 * @return
	 */
	@ConfigurationProperties(prefix = "config.error")
	@Bean
	public UnAuthenticationResp unAuthenticationResp() {
		UnAuthenticationResp resp = new UnAuthenticationResp();
		resp.setCode(ApiConst.Result.UN_AUTHENTICATION);
		return resp;
	}

	/**
	 * 设置null数据不返回
	 * 
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);

		SimpleModule serModule = new SimpleModule();
		objectMapper.registerModule(serModule);

		jsonConverter.setObjectMapper(objectMapper);

		return jsonConverter;
	}

	@RequestMapping
	@ResponseBody
	public String index() {
		return "hello world";
	}
}
