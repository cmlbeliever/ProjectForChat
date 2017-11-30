package com.cml.chat.api.service;

import com.cml.chat.api.model.User;
import com.cml.chat.api.model.request.LoginVO;

public interface UserService {
	/**
	 * 用户登录逻辑处理
	 * 
	 * @param user
	 * @return 系统用户相关信息
	 */
	User login(LoginVO user);
}
