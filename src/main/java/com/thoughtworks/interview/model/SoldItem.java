package com.thoughtworks.interview.model;

/**
 * Item info shows on receipt
 * @author WeiWei
 *
 */
public class SoldItem extends Item {
	
	private int quantity;
	private double sub_total;
	private double savings;
	
	public SoldItem(){
		super();
	}
	
	public SoldItem(Item item, int quantity, double sub_total, double savings) {
		super();
		this.quantity = quantity;
		this.sub_total = sub_total;
		this.savings = savings;
		setItem(item);
	}

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
	
	public double getSavings() {
		return savings;
	}

	public void setSavings(double savings) {
		this.savings = savings;
	}

	public void setItem(Item item){
		setItemName(item.getItemName());
		setItemCategory(item.getItemCategory());
		setItemSerial(item.getItemSerial());
		setDiscount(item.getDiscount());
		setPrice(item.getPrice());
		setUnit(item.getUnit());
	}

}
