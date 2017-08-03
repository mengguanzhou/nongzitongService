package com.mgz.nongzitong.request;

public class LogoutRequest {
	
    private String userId;
    private int type;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
