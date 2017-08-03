package com.mgz.nongzitong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mgz.nongzitong.request.CodeRequest;
import com.mgz.nongzitong.request.FindPswRequest;
import com.mgz.nongzitong.request.RegisterRquest;
import com.mgz.nongzitong.response.CodeResponse;
import com.mgz.nongzitong.response.FindPswResponse;
import com.mgz.nongzitong.response.RegisterResponse;
import com.mgz.nongzitong.service.LoginService;
import com.mgz.nongzitong.service.RegisterService;

@Controller
public class RegisterController {
	
	@Autowired
	RegisterService registerService;
	
	@RequestMapping("/requireCode")
	public @ResponseBody CodeResponse returnCode(@RequestBody String request) {

		CodeRequest codeRequest = JSON.parseObject(request, CodeRequest.class);
		CodeResponse codeResponse = new CodeResponse();
		int temp1 = (int) Math.floor(Math.random() * 10);
		int temp2 = (int) Math.floor(Math.random() * 10);
		int temp3 = (int) Math.floor(Math.random() * 10);
		int temp4 = (int) Math.floor(Math.random() * 10);
		int temp5 = (int) Math.floor(Math.random() * 10);
		int temp6 = (int) Math.floor(Math.random() * 10);
		String code = "";
		code += temp1 + "" + temp2 + "" + temp3 + "" + temp4 + "" + temp5 + "" + temp6;
		int requireResult = registerService.requireCode(codeRequest, code, codeRequest.getType());
		if (requireResult == 1) {
			codeResponse = new CodeResponse(codeRequest.getPhoneNum(), code, 1);
		} else {
			codeResponse = new CodeResponse("", "", -1);
		}
//		CodeResponse codeResponse = new CodeResponse("132456", "1325646", 1);
		return codeResponse;
	}
	
	@RequestMapping("/register")
	public @ResponseBody RegisterResponse registerResult(@RequestBody String request){
		RegisterResponse registerResponse = new RegisterResponse();
		RegisterRquest registerRquest = JSON.parseObject(request, RegisterRquest.class);
		registerResponse = registerService.insertUser(registerRquest);
		return registerResponse;
	}
	
	@RequestMapping("/findPsw")
	public @ResponseBody FindPswResponse findPswResult (@RequestBody String request) {
		FindPswRequest findPswRequest = JSON.parseObject(request, FindPswRequest.class);
		return registerService.findResult(findPswRequest);
	}

}
