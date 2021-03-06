package com.mgz.nongzitong.service;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.mockito.internal.matchers.LessOrEqual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.mgz.nongzitong.request.CodeRequest;
import com.mgz.nongzitong.request.FindPswRequest;
import com.mgz.nongzitong.request.RegisterRquest;
import com.mgz.nongzitong.response.FindPswResponse;
import com.mgz.nongzitong.response.RegisterResponse;

@Service
public class RegisterService {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public RegisterResponse insertUser(RegisterRquest rquest) {
    	if (rquest.getPassword().length() < 6) {
    		RegisterResponse registerResponse = new RegisterResponse();
    		registerResponse.setSuccess(-3);
    		return registerResponse;
    	}
    	int codeCheck = checkCode(rquest.getPhoneNum(), rquest.getCodeNum());
    	if (codeCheck == 1) {
    		String sql = "insert into users (phoneNum, userId, userRole, password, createDate, state) values"
        			+ " (?, ?, ?, ?, ?, 1)";

        	Date now = new Date();
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	String timeNow = simpleDateFormat.format(now);
        	String userId = "";
        	int temp1_1 = (int) Math.floor(Math.random() * 10);
        	int temp1_2 = (int) Math.floor(Math.random() * 10);
        	int temp1_3 = (int) Math.floor(Math.random() * 10);
        	String[] timeArray = timeNow.split(" ");
        	String ymd = timeArray[0].replace("-", "");
        	String[] hms = timeArray[1].split(":");
        	String hm = hms[0] + hms[1];
        	String s = hms[2];
        	if (s.substring(0, 1).equals("0")) {
        		s = s.substring(1, 2);
        	}
        	int temp2_1 = (int) Math.floor(Math.random() * 10);
        	int temp2_2 = (int) Math.floor(Math.random() * 10);
        	int sec = Integer.parseInt(s);
        	int fron = Integer.parseInt(rquest.getPhoneNum().substring(0, 6));
        	int hind = Integer.parseInt(rquest.getPhoneNum().substring(6, 11));
        	int secret = sec * fron / hind;
        	int temp3 = (int) Math.floor(Math.random() * 10);
        	userId += temp1_1 + "" + temp1_2 + "" + temp1_3 + "" + ymd + 
        			 temp2_1 + "" + temp2_2 + "" + hm + secret + temp3;
        	
        	Map<String, Object> temp = new HashMap<String, Object>();
        	temp.put("userId", userId);
        	temp.put("phoneNum", rquest.getPhoneNum());
        	temp.put("userRole", rquest.getUserRole());
        	temp.put("password", rquest.getPassword());
        	temp.put("createDate", now);
        	int returnId = jdbcTemplate.update(sql, new Object[]{rquest.getPhoneNum(), userId, rquest.getUserRole(), rquest.getPassword(), now});
        	if (returnId > 0) {
        		RegisterResponse registerResponse = new RegisterResponse(userId, rquest.getPhoneNum(), rquest.getUserRole(), 1);
        		return registerResponse;
        	} else {
        		RegisterResponse registerResponse = new RegisterResponse();
        		registerResponse.setSuccess(-2);
        		return registerResponse;
        	}
    	} else {
    		RegisterResponse registerResponse = new RegisterResponse();
    		registerResponse.setSuccess(codeCheck);
    		return registerResponse;
    	}
    }
    
    public int requireCode(CodeRequest codeRequest, String code, int type) {
    	if (type == 1) {
    		return codeType1N2(codeRequest, code);
    	} else if (type == 2) {
    		return codeType1N2(codeRequest, code);
    	} else {
    		return 0;
    	}
    }
    
    private int codeType1N2(CodeRequest codeRequest, String code) {
    	String sql = "select enable from codelist where phoneNum = " + codeRequest.getPhoneNum();
    	List<Integer> result = jdbcTemplate.query(sql, new RowMapper<Integer>(){

            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Integer status = rs.getInt("enable");
            	return status;
            }

        });
    	if (result.size() < 1) {
    		String insertCode = "insert into codelist (phoneNum, enable, code) values (?, ?, ?)";
    		int insertResult = jdbcTemplate.update(insertCode, new Object[]{codeRequest.getPhoneNum(), 0, code});
    		if (insertResult > 0) {
    			Timer timer = new Timer();
    			TimerTask task = new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String changeEnable = "update codelist set enable = 1 where phoneNum = " + codeRequest.getPhoneNum();
						jdbcTemplate.update(changeEnable);
					}
				};
				timer.schedule(task, 60000);
    		}
    		return 1;
    	} else if (result.get(0) == 1) {
    		String changeEnable = "update codelist set enable = 0, code = " + code
    				+" where phoneNum = " + codeRequest.getPhoneNum();
			int changeResult = jdbcTemplate.update(changeEnable);
			if (changeResult > 0) {
				Timer timer = new Timer();
    			TimerTask task = new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String changeEnable = "update codelist set enable = 1 where phoneNum = " + codeRequest.getPhoneNum();
						jdbcTemplate.update(changeEnable);
					}
				};
				timer.schedule(task, 60000);
			}
			return 1;
    	} else {
    		return -1;
    	}
    }
    
    public int checkCode(String phoneNum, String code) {
    	String sql = "select code from codelist where phoneNum = " + phoneNum;
    	List<String> codeResult = jdbcTemplate.query(sql, new RowMapper<String>(){

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            	String status = rs.getString("code");
            	return status;
            }

        });
    	if (codeResult.size() < 1) {
    		return 0;
    	} else {
    		if (code.equals(codeResult.get(0))) {
    			return 1;
    		} else {
    			return -1;
    		}
    	}
    }
    
    public FindPswResponse findResult (FindPswRequest findPswRequest) {
    	int check = checkCode(findPswRequest.getPhoneNum(), findPswRequest.getCodeNum());
    	FindPswResponse findPswResponse = new FindPswResponse();
    	findPswResponse.setSuccess(check);
    	if (check == 1) {
    		String changePsw = "update users set password = " + findPswRequest.getPassword() + " where phoneNum = " 
    				+ findPswRequest.getPhoneNum();
    		int changeResult = jdbcTemplate.update(changePsw);
    		if (changeResult > 0) {
    			findPswResponse.setPhoneNum(findPswRequest.getPhoneNum());
    		} else {
    			findPswResponse.setSuccess(-1);
    		}
    	}
    	return findPswResponse;
    }

}
