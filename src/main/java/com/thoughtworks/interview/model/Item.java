package com.thoughtworks.interview.model;

/**
 * Shop item model
 * 
 * @author WeiWei
 * 
 */
public class Item {

	private String itemName;
	private double unitPrice;
	private String itemCategory;
	private String itemSerial;
	private int discount = Discount.NONE;
	
	public Item(){
	}
	
	public Item(String itemName, double unitPrice, String itemCategory,
			String itemSerial) {
		super();
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.itemCategory = itemCategory;
		this.itemSerial = itemSerial;
	}



	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return unitPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.unitPrice = itemPrice;
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String toString() {
		return "{'itemSerial':'" + this.itemSerial + "'," +
				"'itemName':'"+ this.itemName + "'," +
				"'itemPrice':" + this.unitPrice + "," + 
				"'itemCategory':'" + this.itemCategory +"',"+ 
				"'discount':" + this.discount + "}";
	}

}
