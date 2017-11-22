package com.cml.chat.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cml.chat.api.constant.ApiConst;
import com.cml.chat.api.model.UnAuthenticationResp;

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

	@RequestMapping
	@ResponseBody
	public String index() {
		return "hello world";
	}
}
