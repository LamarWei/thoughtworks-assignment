package com.thoughtworks.interview.utils;

import java.math.BigDecimal;

import com.thoughtworks.interview.model.Discount;
import com.thoughtworks.interview.model.Item;
import com.thoughtworks.interview.model.Membership;

/**
 * Calculate item price
 * 
 * @author WeiWei
 * 
 */
public class DiscountCalculator {

	public static double getSubTotal(Item item, int qty) {
		return getSubTotal(item, qty, null);
	}


	public static double getSavings(Item item, int qty) {
		return getSavings(item, qty, null);
	}

	public static double getSubTotal(Item item, int qty, Membership member) {
		double sub_total = 0;
		switch (item.getDiscount()){
			case ALLSALE:
				if (qty > 2) {
					sub_total = chargeBuyTwoGetOneFree(item, qty, member);
				} else {
					sub_total = chargeFivePercentOff(item, qty, member);
				}
				break;
			case BUYTWOGETONEFREE:
				sub_total = chargeBuyTwoGetOneFree(item, qty, member);
				break;
			case FIVEPERCENTOFF:
				sub_total = chargeFivePercentOff(item, qty, member);
				break;
			default:
				sub_total = item.getPrice() * qty;
				break;
		}
		return sub_total;
	}

	public static double getSavings(Item item, int qty, Membership member) {
		double savings;
		/**
		 * If item is available for both two sale, when item match buy two get
		 * another free condition, use this rule then use 5% off rule.
		 */
		switch (item.getDiscount()) {
			case BUYTWOGETONEFREE:
				savings = saveBuyTwoGetOneFree(item, qty, member);
				break;
			case FIVEPERCENTOFF:
				savings = saveFivePercentOff(item, qty, member);
				break;
			case ALLSALE:
				if (qty > 2) {
					savings = saveBuyTwoGetOneFree(item, qty, member);
				} else {
					savings = saveFivePercentOff(item, qty, member);
				}
				break;
			default:
				savings = 0;
				break;
		}
		return savings;
	}
	
	private static double chargeFivePercentOff(Item item, int qty,Membership member) {
		double sub_total;
		if(null == member){
			member = Membership.NOMEMBER;
		}
		if(member.toString() == "NORMALMEMBER"){
			sub_total = item.getPrice() * qty * Math.min(Discount.FIVEPERCENTOFF.getRate(), member.getRate());
		}else{
			sub_total = item.getPrice() * qty * Discount.FIVEPERCENTOFF.getRate() * member.getRate();
		}
		return sub_total;
	}
	
	private static double chargeBuyTwoGetOneFree(Item item, int qty,Membership member) {
		double sub_total =item.getPrice() * (qty - Math.ceil(qty / 3));
		if(null != member && member.toString() == "GOLDMEMBER"){
			sub_total = sub_total * member.getRate();
		}
		return sub_total;
	}
	
	public static double saveFivePercentOff(Item item, int qty, Membership member) {
		if(null == member){
			member = Membership.NOMEMBER;
		}
		double savings = item.getPrice() * qty * new BigDecimal(1-Discount.FIVEPERCENTOFF.getRate()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		switch (member) {
		case GOLDMEMBER:
			savings += (item.getPrice() * qty - savings) * new BigDecimal(1-member.getRate()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			break;
		default:
			savings = item.getPrice() * qty * new BigDecimal(1-Math.min(Discount.FIVEPERCENTOFF.getRate(), member.getRate())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			break;
		}
		return savings;
	}
	
	private static double saveBuyTwoGetOneFree(Item item, int qty, Membership member) {
		double savings = item.getPrice() * Math.ceil(qty / 3);
		if(null != member && member.toString() == "GOLDMEMBER"){
			savings += (item.getPrice() * qty - savings) * new BigDecimal(1-member.getRate()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return savings;
	}
}
