package com.mgz.nongzitong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mgz.nongzitong.request.AddGoodsRequest;
import com.mgz.nongzitong.response.AddGoodsResponse;

@Service
public class GoodsService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public AddGoodsResponse insertGoods (AddGoodsRequest addGoodsRequest) {
		String sql = "insert into goods (goodsId, goodsName, price, unit, image, goodsType, soldType, volume, describe, createDate, solderId,"
				+ "couponType, logisticType, detailType, brandId, scanId) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		AddGoodsResponse addGoodsResponse = new AddGoodsResponse();
		return addGoodsResponse;
	}
	
}
