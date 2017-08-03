package com.mgz.nongzitong.response;

import java.util.ArrayList;

public class CityResponse {
	
    private int success;
    private ArrayList<String> citys;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public ArrayList<String> getCitys() {
		return citys;
	}
	public void setCitys(ArrayList<String> citys) {
		this.citys = citys;
	}
}
