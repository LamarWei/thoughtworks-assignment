package com.thoughtworks.interview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.interview.exception.ItemNotExsitException;
import com.thoughtworks.interview.model.Discount;
import com.thoughtworks.interview.model.Item;
import com.thoughtworks.interview.model.Receipt;
import com.thoughtworks.interview.model.SoldItem;
import com.thoughtworks.interview.service.ItemService;
import com.thoughtworks.interview.utils.CommonTools;
import com.thoughtworks.interview.utils.DiscountCaculator;

@Controller
public class CheckOutController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String viewCheckOutPage(Model model) {
		model.addAttribute("items", itemService.getItems());
		return "index";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public String printReceipt(Model model, HttpServletRequest request) {
		String str = request.getParameter("itemStr");
		/**
		 * Convent items string to json object
		 */
		JSONObject json = CommonTools.StringtoJson(str);
		Receipt receipt = new Receipt();
		List<SoldItem> chargedItems = null;
		try {
			chargedItems = getChargedItems(json);
		} catch (ItemNotExsitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<SoldItem> freeItems = new ArrayList<SoldItem>();
		double total = 0.00;
		double total_savings = 0.00;
		boolean showFreeItemList = false;
		for (String key : json.keySet()) {
			Item item;
			try {
				item = itemService.getItem(key);
			} catch (ItemNotExsitException e) {
				model.addAttribute("items", itemService.getItems());
				model.addAttribute("error", "Item " + key + " not exsit!");
				return "index";
			}
			int qty = json.getIntValue(key);
			double salePrice = DiscountCaculator.getSubTotal(item, qty);
			double saving = DiscountCaculator.getSavings(item, qty);
			total += salePrice;
			total_savings += saving;
			if (CommonTools.isExactDivision(saving / item.getPrice())) {
				showFreeItemList = true;
				freeItems.add(new SoldItem(item, (int) Math.floor(saving
						/ item.getPrice()), 0.00, 0.00));
			}
		}
		receipt.setItems(chargedItems);
		receipt.setFreeItemList(freeItems);
		receipt.setShowFreeItemList(showFreeItemList);
		receipt.setTotal(total);
		receipt.setTotal_savings(total_savings);
		model.addAttribute("receipt", receipt);
		return "receipt";
	}

	public List<SoldItem> getChargedItems(JSONObject json) throws ItemNotExsitException {
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

	@RequestMapping(value = "/discount", method = RequestMethod.PUT)
	@ResponseBody
	public JSONObject setItemDiscount(HttpServletRequest request,
			HttpServletResponse response) {
		String itemSerial = request.getParameter("id");
		JSONObject json = new JSONObject();
		json.put("status", "success");
		Discount discount = Discount.nameOf(request.getParameter("discount"));
		try {
			itemService.setDiscount(itemSerial, discount);
		} catch (ItemNotExsitException e) {
			json.put("status", "fail");
			json.put("error", "Item " + itemSerial + " not exsit!");
		}
		return json;
	}
}
