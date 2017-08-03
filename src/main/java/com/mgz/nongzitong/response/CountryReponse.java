package com.mgz.nongzitong.response;

import java.util.ArrayList;

public class CountryReponse {
	
    private int success;
    private ArrayList<String> countrys;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public ArrayList<String> getCountrys() {
		return countrys;
	}
	public void setCountrys(ArrayList<String> countrys) {
		this.countrys = countrys;
	}

}
