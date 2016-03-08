package com.thoughtworks.interview.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping(value={"/"},method=RequestMethod.GET)
	public String viewCheckOutPage(Model model){
		model.addAttribute("items", itemService.getItems());
		return "index";
	}
	
	@RequestMapping(value={"/"},method=RequestMethod.POST)
	public String printReceipt(Model model,HttpServletRequest request){
		String str = request.getParameter("itemStr");
		/**
		 * Convent items string to json object
		 */
		JSONObject json = CommonTools.StringtoJson(str);
		Receipt receipt = new Receipt();
		List<SoldItem> items = new ArrayList<SoldItem>();
		List<SoldItem> freeItemList = new ArrayList<SoldItem>();
		double total = 0.00;
		double total_savings = 0.00;
		boolean showFreeItemList = false;
			for(String key:json.keySet()){
				Item item;
				try {
					item = itemService.getItem(key);
				} catch (ItemNotExsitException e) {
					model.addAttribute("items", itemService.getItems());
					model.addAttribute("error", "Item "+key+" not exsit!");
					return "index";
				}
				int qty = json.getIntValue(key);
				double salePrice=DiscountCaculator.getSubTotal(item, qty);
				double saving=DiscountCaculator.getSavings(item, qty);
				items.add(new SoldItem(item,qty,salePrice,saving));
				total += salePrice;
				total_savings += saving;
				if(CommonTools.isExactDivision(saving/item.getPrice())){
					showFreeItemList = true;
					freeItemList.add(new SoldItem(item,(int)Math.floor(saving/item.getPrice()),0.00,0.00));
				}
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
	public void setItemDiscount(HttpServletRequest request, HttpServletResponse response){
		String itemSerial = request.getParameter("id");
		String result = "{'status':'success'}";
		int discount = Integer.parseInt(request.getParameter("discount"));
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			itemService.setDiscount(itemSerial, discount);
		} catch (ItemNotExsitException e) {
			result = "{'status':'fail','error':'Item "+itemSerial+" not exsit!'}";
		} catch (IOException e) {
			result = "{'status':'fail','error':'Set fail!'}";
		} finally{
			pw.write(result);
			pw.flush();
		}
	}
}
