package com.thoughtworks.interview.model;

import java.util.List;

public class Receipt {
	
	private final String shopName = "MYSHOP";
	private List<SoldItem> items;
	private List<String> freeItemList;
	private boolean showFreeItemList = false;
	private double total;
	private double total_savings;
	
	public List<SoldItem> getItems() {
		return items;
	}
	public void setItems(List<SoldItem> items) {
		this.items = items;
	}
	public List<String> getFreeItemList() {
		return freeItemList;
	}
	public void setFreeItemList(List<String> freeItemList) {
		this.freeItemList = freeItemList;
	}
	public String getShopName() {
		return shopName;
	}
	public boolean isShowFreeItemList() {
		return showFreeItemList;
	}
	public void setShowFreeItemList(boolean showFreeItemList) {
		this.showFreeItemList = showFreeItemList;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getTotal_savings() {
		return total_savings;
	}
	public void setTotal_savings(double total_savings) {
		this.total_savings = total_savings;
	}
	
	

}
