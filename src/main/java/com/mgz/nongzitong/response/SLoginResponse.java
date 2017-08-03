package com.mgz.nongzitong.response;

public class SLoginResponse {

	private String solderId;
	private String phoneNum;
	private int success;
	public String getSolderId() {
		return solderId;
	}
	public void setSolderId(String solderId) {
		this.solderId = solderId;
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
