package com.mgz.nongzitong.response;

public class LoginResponse {
	
    private String phoneNum;
    private int userRole;
    private String userId;
    private int success;
    
    public LoginResponse () {
    	
    }
    public LoginResponse(String phoneNum, int userRole, String userId, int succes) {
		this.phoneNum = phoneNum;
		this.userRole = userRole;
		this.setUserId(userId);
		this.success = succes;
	}
    
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
