package com.thoughtworks.interview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.interview.exception.ItemNotExsitException;
import com.thoughtworks.interview.model.Item;
import com.thoughtworks.interview.model.Receipt;
import com.thoughtworks.interview.model.SoldItem;
import com.thoughtworks.interview.service.ItemService;
import com.thoughtworks.interview.utils.DataConventor;
import com.thoughtworks.interview.utils.DiscountCaculator;

@Controller
public class CheckOutController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping(value={"/index","/"},method=RequestMethod.GET)
	public String viewCheckOutPage(Model model){
		model.addAttribute("items", itemService.getItems());
		return "index";
	}
	
	@RequestMapping(value={"/index","/"},method=RequestMethod.POST)
	public String printReceipt(Model model,HttpServletRequest request){
		String str = request.getParameter("itemStr");
		/**
		 * Convent items string to json object
		 */
		JSONObject json = DataConventor.StringtoJson(str);
		Receipt receipt = new Receipt();
		List<SoldItem> items = new ArrayList<SoldItem>();
		List<String> freeItemList = new ArrayList<String>();
		double total = 0.00;
		double total_savings = 0.00;
		boolean showFreeItemList = false;
		try {
			for(String key:json.keySet()){
				Item item = itemService.getItem(key);
				int qty = json.getIntValue(key);
				double salePrice=DiscountCaculator.getSalePrice(item, qty);
				double saving=DiscountCaculator.getSavings(item, qty);
				items.add(new SoldItem(item,qty,salePrice,saving));
				total += salePrice;
				total_savings += saving;
				if(DataConventor.isInteger(String.valueOf(saving/item.getPrice()))){
					showFreeItemList = true;
					freeItemList.add(item.getItemName()+","+(saving/item.getPrice()));
				}
			}
		} catch (ItemNotExsitException e) {
			e.printStackTrace();
			model.addAttribute("error", "Item not exsit!");
			return "index";
		}
		receipt.setItems(items);
		receipt.setFreeItemList(freeItemList);
		receipt.setShowFreeItemList(showFreeItemList);
		receipt.setTotal(total);
		receipt.setTotal_savings(total_savings);
		model.addAttribute("receipt", receipt);
		return "receipt";
	}
	
	@RequestMapping(value="/setdiscount",method=RequestMethod.POST)
	public String setItemDiscount(HttpServletRequest request, Model model){
		String itemSerial = request.getParameter("id");
		int discount = Integer.parseInt(request.getParameter("discount"));
		try {
			itemService.setDiscount(itemSerial, discount);
		} catch (ItemNotExsitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("error", "Item not exsit!");
		}
		return "redirect:/";
	}
}
