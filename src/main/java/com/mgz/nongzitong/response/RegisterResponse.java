package com.mgz.nongzitong.response;

public class RegisterResponse {
	
	private String userId;
	private String phoneNum;
	private int userRole;
	private int success;
	
	public RegisterResponse() {}
	public RegisterResponse(String userId, String phoneNum, int userRole, int success) {
		this.userId = userId;
		this.phoneNum = phoneNum;
		this.userRole = userRole;
		this.success = success;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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

}
