package com.thoughtworks.interview.model;

import java.util.List;

public class Receipt {
	
	private final String shopName = "MYSHOP";
	private List<SoldItem> items;
	private boolean isDiscount = false;
	
	public List<SoldItem> getItems() {
		return items;
	}
	public void setItems(List<SoldItem> items) {
		this.items = items;
	}
	public boolean isDiscount() {
		return isDiscount;
	}
	public void setDiscount(boolean isDiscount) {
		this.isDiscount = isDiscount;
	}
	public String getShopName() {
		return shopName;
	}
	
	

}
