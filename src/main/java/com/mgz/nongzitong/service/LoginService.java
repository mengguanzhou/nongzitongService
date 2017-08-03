package com.mgz.nongzitong.service;

import static org.mockito.Matchers.intThat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.type.MapType;
import com.mgz.nongzitong.request.LoginRequest;

@Service
public class LoginService {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public int CheckLogin(LoginRequest loginRequest) {
		String checkPassword = "select password from users where phoneNum = " + loginRequest.getPhoneNum();
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
	
	public Map<String, String> getLoginINfo(String phoneNum) {
		String searchUser = "select userRole, userId from users where phoneNum = " + phoneNum;
		List<Map<String, String>> userInfo = jdbcTemplate.query(searchUser, new RowMapper<Map<String, String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int arg1) throws SQLException {
				Map<String, String> mapTemp = new HashMap<String, String>();
				mapTemp.put("userRole", rs.getInt("userRole") + "");
				mapTemp.put("userId", rs.getString("userId"));
				return mapTemp;
			}
		});
		return userInfo.get(0);
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
