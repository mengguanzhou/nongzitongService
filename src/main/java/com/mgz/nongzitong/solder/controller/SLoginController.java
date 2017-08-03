package com.mgz.nongzitong.solder.controller;

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
import com.mgz.nongzitong.response.SLoginResponse;
import com.mgz.nongzitong.response.SimpleReponse;
import com.mgz.nongzitong.solder.service.SLogingService;

@Controller
public class SLoginController {
	
	@Autowired
	SLogingService loginService;
	
	@RequestMapping("/solder/login")
	public @ResponseBody SLoginResponse loginResult(@RequestBody String request) {
		LoginRequest loginRequest = JSON.parseObject(request, LoginRequest.class);
		int check = loginService.CheckLogin(loginRequest);
		SLoginResponse loginResponse = new SLoginResponse();
		loginResponse.setSuccess(check);
		if (check == 1) {
			loginResponse.setPhoneNum(loginRequest.getPhoneNum());
			loginResponse.setSolderId(loginService.findSolderId(loginRequest.getPhoneNum()));
		}
		return loginResponse;
	}
	
	@RequestMapping("/solder/logout")
	public @ResponseBody SimpleReponse logoutResult(@RequestBody String request) {
		LogoutRequest logoutRequest = JSON.parseObject(request, LogoutRequest.class);
		SimpleReponse logoutResponse = new SimpleReponse();
		logoutResponse.setSuccess(loginService.logout(logoutRequest.getUserId()));
		return logoutResponse;
	}

}
