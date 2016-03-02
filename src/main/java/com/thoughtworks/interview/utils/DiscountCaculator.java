package com.thoughtworks.interview.utils;

import com.thoughtworks.interview.model.Item;

public class DiscountCaculator {

	public static double getSalePrice(Item item, int qty) {
		double sub_total;
		switch (item.getDiscount()) {
		case 1:
			sub_total = item.getPrice() * (qty - Math.ceil(qty/ 3)) ;
			break;
		case 2:
			sub_total = item.getPrice() * 0.95 * qty;
			break;
		default:
			sub_total = item.getPrice() * qty;
			break;
		}
		return sub_total;
	}
	
	public static double getSavings(Item item, int qty){
		double savings;
		switch (item.getDiscount()) {
		case 1:
			savings = item.getPrice() * Math.ceil(qty/ 3) ;
			break;
		case 2:
			savings = item.getPrice() * 0.05 * qty;
			break;
		default:
			savings = 0;
			break;
		}
		return savings;
	}

}
