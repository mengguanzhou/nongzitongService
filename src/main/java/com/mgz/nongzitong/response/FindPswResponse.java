package com.mgz.nongzitong.response;

public class FindPswResponse {
	
    private String phoneNum;
    private int success;
    
    public FindPswResponse() {
		
	}
    public FindPswResponse(String phoneNum, int success) {
		this.phoneNum = phoneNum;
		this.success = success;
	}
    
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
}
