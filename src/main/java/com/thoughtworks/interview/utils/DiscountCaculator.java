package com.thoughtworks.interview.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.interview.exception.ItemNotExsitException;
import com.thoughtworks.interview.model.Discount;
import com.thoughtworks.interview.model.Item;
import com.thoughtworks.interview.model.SoldItem;
import com.thoughtworks.interview.service.ItemService;

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
		double fivepercent = new BigDecimal(1-Discount.FIVEPERCENTOFF.getRate()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		/**
		 * If item is available for both two sale, when item match buy two get
		 * another free condition, use this rule then use 5% off rule.
		 */
		switch (item.getDiscount()) {
			case BUYTWOGETONEFREE:
				savings = item.getPrice() * Math.ceil(qty / 3);
				break;
			case FIVEPERCENTOFF:
				savings = item.getPrice() * fivepercent * qty;
				break;
			case ALLSALE:
				if (qty > 2) {
					savings = item.getPrice() *  Math.ceil(qty / 3);
				} else {
					savings = item.getPrice() * fivepercent * qty;
				}
				break;
			default:
				savings = 0;
				break;
		}
		return savings;
	}
	
	public static double getSavings(JSONObject json, ItemService itemService) throws ItemNotExsitException {
		double total_savings = 0.00;
		for (String key : json.keySet()) {
			Item item = itemService.getItem(key);
			int qty = json.getIntValue(key);
			double saving = DiscountCaculator.getSavings(item, qty);
			total_savings += saving;
		}
		return total_savings;
	}

	public static double getTotal(JSONObject json, ItemService itemService)	throws ItemNotExsitException {
		double total = 0;
		for (String key : json.keySet()) {
			Item item = itemService.getItem(key);
			int qty = json.getIntValue(key);
			double salePrice = DiscountCaculator.getSubTotal(item, qty);
			total += salePrice;
		}
		return total;
	}
	
	public static List<SoldItem> getFreeItems(JSONObject json, ItemService itemService)
			throws ItemNotExsitException {
		List<SoldItem> freeItems = new ArrayList<SoldItem>();
		for (String key : json.keySet()) {
			Item item = itemService.getItem(key);
			int qty = json.getIntValue(key);
			double saving = DiscountCaculator.getSavings(item, qty);
			if (CommonTools.isExactDivision(saving / item.getPrice())) {
				freeItems.add(new SoldItem(item, (int) Math.floor(saving
						/ item.getPrice()), 0.00, 0.00));
			}
		}
		return freeItems;
	}

	public static List<SoldItem> getChargedItems(JSONObject json, ItemService itemService)
			throws ItemNotExsitException {
		List<SoldItem> chargedItems = new ArrayList<SoldItem>();
		for (String key : json.keySet()) {
			Item item = itemService.getItem(key);
			int qty = json.getIntValue(key);
			double salePrice = DiscountCaculator.getSubTotal(item, qty);
			double saving = DiscountCaculator.getSavings(item, qty);
			chargedItems.add(new SoldItem(item, qty, salePrice, saving));
		}
		return chargedItems;
	}

}
