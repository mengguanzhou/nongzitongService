package com.mgz.nongzitong.request;

public class RegisterRquest {
	
	private String phoneNum;
	private int userRole;
	private String password;
	private String codeNum;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
}
