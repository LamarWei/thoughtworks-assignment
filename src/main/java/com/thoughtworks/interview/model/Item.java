package com.thoughtworks.interview.model;

/**
 * Shop item model
 * 
 * @author WeiWei
 * 
 */
public class Item {

	private String itemName;
	private double price;
	private String unit;
	private String itemCategory;
	private String itemSerial;
	private Discount discount = Discount.NODISCOUNT;
	
	public Item(){
	}
	
	public Item(String itemName, double price, String unit, String itemCategory,
			String itemSerial) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.unit = unit;
		this.itemCategory = itemCategory;
		this.itemSerial = itemSerial;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSerial() {
		return itemSerial;
	}

	public void setItemSerial(String itemSerial) {
		this.itemSerial = itemSerial;
	}


	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public String toString() {
		return "{'itemSerial':'" + this.itemSerial + "'," +
				"'itemName':'"+ this.itemName + "'," +
				"'itemPrice':" + this.price + "," + 
				"'itemCategory':'" + this.itemCategory +"',"+ 
				"'discount':" + this.discount + "}";
	}

}
