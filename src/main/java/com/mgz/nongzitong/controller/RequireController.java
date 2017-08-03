package com.mgz.nongzitong.controller;

import static org.mockito.Mockito.validateMockitoUsage;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mgz.nongzitong.request.CategoryRequest;
import com.mgz.nongzitong.response.BrandResponse;
import com.mgz.nongzitong.response.CategoryResponse;
import com.mgz.nongzitong.service.RequireService;
import com.mysql.fabric.xmlrpc.base.Data;

@Controller
public class RequireController {
	
	@Autowired
	RequireService requireService;
	
	@RequestMapping("/requireCategory")
	public @ResponseBody CategoryResponse categoryResult(@RequestBody String request) {
		CategoryRequest categoryRequest = JSON.parseObject(request, CategoryRequest.class);
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse = requireService.queryCategory(categoryRequest.getType());
		return categoryResponse;
	}
	
	@RequestMapping("/requireBrand")
	public @ResponseBody BrandResponse brandResult(@RequestBody String request) {
		BrandResponse brandResponse = new BrandResponse();
		brandResponse = requireService.queryBrand();
		return brandResponse;
	}

}
