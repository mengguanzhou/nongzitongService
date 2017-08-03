package com.mgz.nongzitong.response;

public class SRegisterResponse {
    private String solderId;
    private int success;
    
    public SRegisterResponse() {
    	
    }
    public SRegisterResponse(String solderId,int success) {
		this.solderId = solderId;
		this.success = success;
	}
    
	public String getSolderId() {
		return solderId;
	}
	public void setSolderId(String solderId) {
		this.solderId = solderId;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}

}
