package com.cml.chat.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cml.chat.api.model.User;
import com.cml.chat.api.model.request.LoginVO;
import com.cml.chat.api.model.resp.LoginResp;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	private String token;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * 用户登录测试，但是无法使用到的过滤器
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLogin() throws Exception {
		LoginVO loginUser = new LoginVO();
		loginUser.setUsername("guest");
		loginUser.setPassword("111111");

		Gson gson = new Gson();
		RequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8080/user/login")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(gson.toJson(loginUser));

		MvcResult mvcResult = mockMvc.perform(request).andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("返回数据：" + content + ",status:" + status);

		Assert.assertTrue(status == 200);

		LoginResp resp = gson.fromJson(content, LoginResp.class);
		Assert.assertEquals(resp.getStatus(), 200);

		token = resp.getToken();

		// 测试用户信息获取
		System.out.println("测试返回getUserInfo=============>token:" + token);

		request = MockMvcRequestBuilders.post("http://localhost:8080/user/getUserInfo").header("token", token)
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		mvcResult = mockMvc.perform(request).andReturn();

		status = mvcResult.getResponse().getStatus();
		content = mvcResult.getResponse().getContentAsString();
		System.out.println("返回数据：" + content + ",status:" + status);

		Assert.assertTrue(status == 200);
		Assert.assertNotNull(content);

		User tokenUser = gson.fromJson(content, User.class);
		Assert.assertEquals(tokenUser.getUsername(), "guest");

	}

}
