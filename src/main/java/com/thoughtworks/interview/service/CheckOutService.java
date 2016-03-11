package com.thoughtworks.interview.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.interview.exception.ItemNotExsitException;
import com.thoughtworks.interview.model.SoldItem;

public interface CheckOutService {
	
	double getSavings(JSONObject json, ItemService itemService) throws ItemNotExsitException;
	
	double getTotal(JSONObject json, ItemService itemService) throws ItemNotExsitException;
	
	List<SoldItem> getFreeItems(JSONObject json, ItemService itemService) throws ItemNotExsitException;
	
	List<SoldItem> getChargedItems(JSONObject json, ItemService itemService) throws ItemNotExsitException;

}