package com.cml.chat.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cml.chat.api.auth.JwtManager;
import com.cml.chat.api.constant.ApiConst;
import com.cml.chat.api.model.User;
import com.cml.chat.api.model.request.LoginVO;
import com.cml.chat.api.model.resp.LoginResp;
import com.cml.chat.api.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JwtManager jwtManager;

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public LoginResp login(LoginVO loginUser) {

		// 设置返回结果
		LoginResp resp = new LoginResp();

		// 步骤1：请求参数校验
		if (StringUtils.isEmpty(loginUser.getUsername())) {
			resp.setCode(ApiConst.Result.FAIL);
			resp.setErrMsg("用户名为空");
			return resp;
		} else if (StringUtils.isEmpty(loginUser.getPassword())) {
			resp.setCode(ApiConst.Result.FAIL);
			resp.setErrMsg("密码为空");
			return resp;
		}

		// 这里就不从DB中查了，直接生成个假数据
		User u = userService.login(loginUser);
		if (null == u) {
			resp.setCode(ApiConst.Result.FAIL);
			resp.setErrMsg("用户名或密码错误！！！");
			return resp;
		}

		resp.setCode(ApiConst.Result.OK);
		resp.setToken(jwtManager.generateToken(u));
		return resp;
	}
}
