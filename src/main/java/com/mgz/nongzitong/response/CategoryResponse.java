package com.mgz.nongzitong.response;

import java.util.ArrayList;
import java.util.Map;

public class CategoryResponse {
	private ArrayList<String> categoryList;
    private int success;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public ArrayList<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList<String> categoryList) {
		this.categoryList = categoryList;
	}
}
