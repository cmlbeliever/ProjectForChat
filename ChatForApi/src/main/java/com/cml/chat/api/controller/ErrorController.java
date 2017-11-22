package com.cml.chat.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cml.chat.api.model.resp.UnAuthenticationResp;

@RequestMapping("/error")
@Controller
public class ErrorController {
	@Autowired
	private UnAuthenticationResp unAuthenticationResp;
	
	@Value("${config.error.errMsg}")
	private String msg;

	@RequestMapping("/unAuth")
	@ResponseBody
	public UnAuthenticationResp unAuthenticationResp() {
		System.out.println(unAuthenticationResp.getErrMsg()+","+msg);
		return unAuthenticationResp;
	}
}
