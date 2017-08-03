package com.mgz.nongzitong.response;

public class CodeResponse {
	
    private String phoneNum;
    private String codeNum;
    private int success;
    
    public CodeResponse() {
    	
    }
    public CodeResponse(String phoneNum, String codeNum, int success) {
    	this.phoneNum = phoneNum;
    	this.codeNum = codeNum;
    	this.success = success;
    }
    
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}

}
