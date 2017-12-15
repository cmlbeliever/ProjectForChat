package com.cml.chat.lesson.lesson7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloWorldController {

	@RequestMapping("/hw")
	@ResponseBody
	public String testA() {
		return "testFunciton1!!";
	}

}
