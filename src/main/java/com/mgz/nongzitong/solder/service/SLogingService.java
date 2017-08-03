package com.mgz.nongzitong.solder.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.mgz.nongzitong.request.LoginRequest;

@Service
public class SLogingService {


	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public int CheckLogin(LoginRequest loginRequest) {
		String checkPassword = "select password from solder where phoneNum = " + loginRequest.getPhoneNum();
		List<String> password = jdbcTemplate.query(checkPassword, new RowMapper<String>(){
		@Override
		public String mapRow(ResultSet rs, int arg1) throws SQLException {
			return rs.getString("password");
		}});
		if (password.size() < 1) {
			return 0;
		} else if (password.get(0).equals(loginRequest.getPassword())) {
			String updateState = "update users set state = 1 where phoneNum = " + loginRequest.getPhoneNum();
			int result = jdbcTemplate.update(updateState);
			if (result > 0)
			return 1;
			else return 0;
		} else {
			return -1;
		}
	}
	
	public String findSolderId(String phoneNum) {
		String findSIdSql = "select solderId from solder where phoneNum = " + phoneNum;
		List<String> solderId = jdbcTemplate.query(findSIdSql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("solderId");
			}});
		if (solderId.size() > 0) {
			return solderId.get(0);
		} else {
			return "";
		}
	}
	
	public int logout(String userId) {
		String logoutSql = "update users set state = 0 where userId = " + userId;
		int logoutResult = jdbcTemplate.update(logoutSql);
		if (logoutResult > 0) {
			return 1;
		} else {
			return -1;
		}
	}
}
