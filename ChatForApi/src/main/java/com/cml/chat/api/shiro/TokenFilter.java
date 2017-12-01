package com.cml.chat.api.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cml.chat.api.auth.JwtManager;
import com.cml.chat.api.constant.ApiConst;
import com.cml.chat.api.model.User;
import com.google.gson.Gson;

public class TokenFilter extends AccessControlFilter {
	private static Logger log = LoggerFactory.getLogger(TokenFilter.class);

	protected String unauthorizedUrl;

	private JwtManager jwtManager;

	public TokenFilter(JwtManager jwtManager) {
		super();
		this.jwtManager = jwtManager;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

		HttpServletRequest req = (HttpServletRequest) request;

		String token = req.getHeader(ApiConst.Request.TOKEN_KEY);

		log.debug("=======>TokenFilter ,getUnauthorizedUrl=" + this.getUnauthorizedUrl() + "," + mappedValue + "," + req.getRequestURI().toString());

		if (StringUtils.isNotBlank(token)) {

			try {
				User user = new Gson().fromJson(jwtManager.parseToken(token), User.class);
				request.setAttribute(ApiConst.Request.TOKEN_KEY, user);
			} catch (Exception e) {
				log.warn("=======>TokenFilter校验失败", e);
				return false;
			}

			return true;
		}

		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		WebUtils.redirectToSavedRequest(request, response, getUnauthorizedUrl());
		return false;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

}
