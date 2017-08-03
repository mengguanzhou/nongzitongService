package com.mgz.nongzitong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mgz.nongzitong.request.AddGoodsRequest;
import com.mgz.nongzitong.response.AddGoodsResponse;
import com.mgz.nongzitong.response.BrandResponse;
import com.mgz.nongzitong.response.CategoryResponse;

@Controller
public class GoodsController {

	@RequestMapping("/solder/addGood")
	public @ResponseBody AddGoodsResponse addResult (@RequestBody String request) {
		AddGoodsRequest addGoodsRequest = JSON.parseObject(request, AddGoodsRequest.class);
		
		AddGoodsResponse addGoodsResponse = new AddGoodsResponse();
		return addGoodsResponse;
	}
}
