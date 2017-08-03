package com.mgz.nongzitong.service;

import static org.mockito.Matchers.intThat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public ArrayList<String> findCity() {
		String findCitySql = "select * from city";
		List<String> citys = new ArrayList<String>();
		citys = jdbcTemplate.query(findCitySql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("city");
			}
		});
		return (ArrayList<String>) citys;
	}
	
	public ArrayList<String> findCountry(int city) {
		String findCountrySql = "select * from country" + city;
		List<String> countrys = new ArrayList<String>();
		countrys = jdbcTemplate.query(findCountrySql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getString("country");
			}
		});
		return (ArrayList<String>) countrys;
	}
}
