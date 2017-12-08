package com.cml.chat.lesson.lesson6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cml.chat.lesson.extra.ExtraBean;

@RequestMapping("/")
@Controller
public class SimpleController {

//	@Autowired
//	private ExtraBean extraBean;

	@RequestMapping
	@ResponseBody
	public String index() {
		return "hello world!!!";
	}
}
