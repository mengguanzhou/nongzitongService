package com.mgz.nongzitong.solder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mgz.nongzitong.request.CodeRequest;
import com.mgz.nongzitong.request.FindPswRequest;
import com.mgz.nongzitong.request.RegisterRquest;
import com.mgz.nongzitong.request.SRegisterRequest;
import com.mgz.nongzitong.response.CodeResponse;
import com.mgz.nongzitong.response.FindPswResponse;
import com.mgz.nongzitong.response.RegisterResponse;
import com.mgz.nongzitong.response.SRegisterResponse;
import com.mgz.nongzitong.service.RegisterService;
import com.mgz.nongzitong.solder.service.SRegisterService;

@Controller
public class SRegisterController {
	

	@Autowired
	SRegisterService registerService;
	
	@RequestMapping("/solder/register")
	public @ResponseBody SRegisterResponse registerResult(@RequestBody String request){
		SRegisterResponse registerResponse = new SRegisterResponse();
		SRegisterRequest registerRquest = JSON.parseObject(request, SRegisterRequest.class);
		registerResponse = registerService.inserSolder(registerRquest);
		return registerResponse;
	}
	
	@RequestMapping("/solder/findPsw")
	public @ResponseBody FindPswResponse findPswResult (@RequestBody String request) {
		FindPswRequest findPswRequest = JSON.parseObject(request, FindPswRequest.class);
		return registerService.findResult(findPswRequest);
	}
}
