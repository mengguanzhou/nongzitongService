package com.mgz.nongzitong.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mgz.nongzitong.request.LoginRequest;
import com.mgz.nongzitong.request.LogoutRequest;
import com.mgz.nongzitong.response.LoginResponse;
import com.mgz.nongzitong.response.SimpleReponse;
import com.mgz.nongzitong.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/login")
	public @ResponseBody LoginResponse loginResult(@RequestBody String request) {
		LoginRequest loginRequest = JSON.parseObject(request, LoginRequest.class);
		int check = loginService.CheckLogin(loginRequest);
		LoginResponse loginResponse = new LoginResponse();
		switch (check) {
			case -1:
				loginResponse.setSuccess(-1);
				break;
			case 0:
				loginResponse.setSuccess(0);
				break;
			case 1:
				Map<String, String> userInfo = loginService.getLoginINfo(loginRequest.getPhoneNum());
				loginResponse = new LoginResponse(loginRequest.getPhoneNum(), Integer.parseInt(userInfo.get("userRole")), userInfo.get("userId"), 1);
				break;
			default:
				break;
		}
		return loginResponse;
	}
	
	@RequestMapping("/logout")
	public @ResponseBody SimpleReponse logoutResult(@RequestBody String request) {
		LogoutRequest logoutRequest = JSON.parseObject(request, LogoutRequest.class);
		SimpleReponse logoutResponse = new SimpleReponse();
		logoutResponse.setSuccess(loginService.logout(logoutRequest.getUserId()));
		return logoutResponse;
	}

}
