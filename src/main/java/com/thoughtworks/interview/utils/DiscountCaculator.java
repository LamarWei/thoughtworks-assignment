package com.thoughtworks.interview.utils;

import com.thoughtworks.interview.model.Discount;
import com.thoughtworks.interview.model.Item;

/**
 * Calculate item price
 * 
 * @author WeiWei
 *
 */
public class DiscountCaculator {

	public static double getSubTotal(Item item, int qty) {
		double sub_total;
		int discount = item.getDiscount();
		/**
		 * If item is available for both two sale,
		 * when item match buy two get another free condition, use this rule
		 * then use 5% off rule.
		 */
		if(item.getDiscount() == 3){
			if(qty>2){
				discount = Discount.BUYTWOGETANOTHERFREE;
			}
			else{
				discount = (Discount.FIVEPERCENTOFF);
			}
		}
		switch (discount) {
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
		int discount = item.getDiscount();
		/**
		 * If item is available for both two sale,
		 * when item match buy two get another free condition, use this rule
		 * then use 5% off rule.
		 */
		if(item.getDiscount() == 3){
			if(qty>2){
				discount = Discount.BUYTWOGETANOTHERFREE;
			}
			else{
				discount = (Discount.FIVEPERCENTOFF);
			}
		}
		switch (discount) {
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
