package com.mgz.nongzitong.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.mgz.nongzitong.response.BrandResponse;
import com.mgz.nongzitong.response.CategoryResponse;

@Service
public class RequireService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public BrandResponse queryBrand() {
		BrandResponse brandResponse = new BrandResponse();
		String sql = "select brandName from brands order by brandId asc";
		List<String> brands = new ArrayList<String>();
		brands = jdbcTemplate.query(sql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("brandName");
			}});
		if (brands.size() > 0) {
			brandResponse.setBrandList((ArrayList<String>)brands);
			brandResponse.setSuccess(1);
		} else {
			brandResponse.setSuccess(-1);
		}
		return brandResponse;
	}
	
	public CategoryResponse queryCategory(int type) {
		CategoryResponse categoryResponse = new CategoryResponse();
		String sql = "select * from categories where type = " + type;
		List<String> categories = new ArrayList<String>();
		categories = jdbcTemplate.query(sql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("name");
			}});
		if (categories.size() > 0) {
			categoryResponse.setCategoryList((ArrayList<String>)categories);
			categoryResponse.setSuccess(1);
		} else {
			categoryResponse.setSuccess(-1);
		}
		return categoryResponse;
	}
}
