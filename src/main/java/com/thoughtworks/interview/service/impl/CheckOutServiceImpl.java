package com.thoughtworks.interview.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.interview.exception.ItemNotExsitException;
import com.thoughtworks.interview.model.Item;
import com.thoughtworks.interview.model.SoldItem;
import com.thoughtworks.interview.service.CheckOutService;
import com.thoughtworks.interview.service.ItemService;
import com.thoughtworks.interview.utils.CommonTools;
import com.thoughtworks.interview.utils.DiscountCalculator;

public class CheckOutServiceImpl implements CheckOutService {

	public double getSavings(JSONObject json, ItemService itemService) throws ItemNotExsitException {
		double total_savings = 0.00;
		for (String key : json.keySet()) {
			Item item = itemService.getItem(key);
			int qty = json.getIntValue(key);
			double saving = DiscountCalculator.getSavings(item, qty);
			total_savings += saving;
		}
		return total_savings;
	}

	public double getTotal(JSONObject json, ItemService itemService) throws ItemNotExsitException {
		double total = 0;
		for (String key : json.keySet()) {
			Item item = itemService.getItem(key);
			int qty = json.getIntValue(key);
			double salePrice = DiscountCalculator.getSubTotal(item, qty);
			total += salePrice;
		}
		return total;
	}
	
	public List<SoldItem> getFreeItems(JSONObject json, ItemService itemService) throws ItemNotExsitException {
		List<SoldItem> freeItems = new ArrayList<SoldItem>();
		for (String key : json.keySet()) {
			Item item = itemService.getItem(key);
			int qty = json.getIntValue(key);
			double saving = DiscountCalculator.getSavings(item, qty);
			if (CommonTools.isExactDivision(saving / item.getPrice())) {
				freeItems.add(new SoldItem(item, (int) Math.floor(saving
						/ item.getPrice()), 0.00, 0.00));
			}
		}
		return freeItems;
	}

	public List<SoldItem> getChargedItems(JSONObject json, ItemService itemService)	throws ItemNotExsitException {
		List<SoldItem> chargedItems = new ArrayList<SoldItem>();
		for (String key : json.keySet()) {
			Item item = itemService.getItem(key);
			int qty = json.getIntValue(key);
			double salePrice = DiscountCalculator.getSubTotal(item, qty);
			double saving = DiscountCalculator.getSavings(item, qty);
			chargedItems.add(new SoldItem(item, qty, salePrice, saving));
		}
		return chargedItems;
	}
}
