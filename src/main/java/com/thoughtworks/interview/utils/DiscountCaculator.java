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
		/**
		 * If item is available for both two sale, when item match buy two get
		 * another free condition, use this rule then use 5% off rule.
		 */
		switch (item.getDiscount()) {
			case BUYTWOGETONEFREE:
				sub_total = item.getPrice() * (qty - Math.ceil(qty / 3));
				break;
			case FIVEPERCENTOFF:
				sub_total = item.getPrice() * Discount.FIVEPERCENTOFF.getRate() * qty;
				break;
			case ALLSALE:
				if (qty > 2) {
					sub_total = item.getPrice() * (qty - Math.ceil(qty / 3));
				} else {
					sub_total = item.getPrice() * Discount.FIVEPERCENTOFF.getRate() * qty;
				}
				break;
			default:
				sub_total = item.getPrice() * qty;
				break;
		}
		return sub_total;
	}

	public static double getSavings(Item item, int qty) {
		double savings;
		/**
		 * If item is available for both two sale, when item match buy two get
		 * another free condition, use this rule then use 5% off rule.
		 */
		switch (item.getDiscount()) {
			case BUYTWOGETONEFREE:
				savings = item.getPrice() * Math.ceil(qty / 3);
				break;
			case FIVEPERCENTOFF:
				savings = item.getPrice() * (1 - Discount.FIVEPERCENTOFF.getRate()) * qty;
				break;
			case ALLSALE:
				if (qty > 2) {
					savings = item.getPrice() *  Math.ceil(qty / 3);
				} else {
					savings = item.getPrice() * (1 - Discount.FIVEPERCENTOFF.getRate()) * qty;
				}
				break;
			default:
				savings = 0;
				break;
		}
		return savings;
	}

}
