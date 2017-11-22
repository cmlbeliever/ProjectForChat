package com.cml.chat.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cml.chat.api.auth.JwtManager;
import com.cml.chat.api.constant.ApiConst;
import com.cml.chat.api.model.User;
import com.cml.chat.api.model.request.LoginVO;
import com.cml.chat.api.model.resp.LoginResp;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JwtManager jwtManager;

	@RequestMapping("/loginByJwt")
	@ResponseBody
	public LoginResp login(LoginVO loginUser) {

		// 设置个假用户的数据
		User user = new User();
		user.setUsername(user.getUsername());
		user.setNickName("dummy nickname");
		user.setUserId(11111L);
		// 设置返回结果
		LoginResp resp = new LoginResp();
		resp.setCode(ApiConst.Result.OK);
		resp.setToken(jwtManager.generateToken(user));
		return resp;
	}
}
