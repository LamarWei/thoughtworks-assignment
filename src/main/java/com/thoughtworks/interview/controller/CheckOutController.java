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
import com.thoughtworks.interview.model.Receipt;
import com.thoughtworks.interview.model.SoldItem;
import com.thoughtworks.interview.service.CheckOutService;
import com.thoughtworks.interview.service.ItemService;
import com.thoughtworks.interview.utils.CommonTools;
import com.thoughtworks.interview.utils.DiscountCalculator;

@Controller
public class CheckOutController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private CheckOutService checkOutService;

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
		List<SoldItem> freeItems = new ArrayList<SoldItem>();
		double total = 0.00;
		double total_savings = 0.00;
		try {
			chargedItems = checkOutService.getChargedItems(json,itemService);
			freeItems = checkOutService.getFreeItems(json,itemService);
			total = checkOutService.getTotal(json,itemService);
			total_savings = checkOutService.getSavings(json,itemService);
		} catch (ItemNotExsitException e) {
			model.addAttribute("items", itemService.getItems());
			model.addAttribute("error", "Item not exsit!");
			return "index";
		}
		
		receipt.setItems(chargedItems);
		receipt.setFreeItemList(freeItems);
		receipt.setTotal(total);
		receipt.setTotal_savings(total_savings);
		model.addAttribute("receipt", receipt);
		return "receipt";
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
