package com.cml.chat.api.constant;

public interface ApiConst {
	interface Result {
		int OK = 200;
		int FAIL = 400;
		int UN_AUTHENTICATION = 400;
	}

	interface Request {
		String TOKEN_KEY = "token";
	}
}
