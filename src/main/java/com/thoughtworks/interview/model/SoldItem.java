package com.thoughtworks.interview.model;

/**
 * Item info shows on receipt
 * @author WeiWei
 *
 */
public class SoldItem extends Item {
	
	private int quantity;
	private double sub_total;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSub_total() {
		return sub_total;
	}
	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}
	

}
