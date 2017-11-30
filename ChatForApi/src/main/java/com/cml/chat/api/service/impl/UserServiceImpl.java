package com.cml.chat.api.service.impl;

import org.springframework.stereotype.Service;

import com.cml.chat.api.model.User;
import com.cml.chat.api.model.request.LoginVO;
import com.cml.chat.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User login(LoginVO loginUser) {
		User user = new User();
		user.setUsername(loginUser.getUsername());
		user.setNickName("dummy nickname");
		user.setUserId(11111L);
		return user;
	}

}
