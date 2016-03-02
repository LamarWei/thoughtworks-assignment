package com.thoughtworks.interview.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.interview.model.Item;
import com.thoughtworks.interview.model.SoldItem;
import com.thoughtworks.interview.service.CheckOutService;

public class CheckOutServiceImpl implements CheckOutService {

	@Override
	public void checkOut(JSONObject json, Map<String, Item> map) {
		// TODO Auto-generated method stub
		Set<String> keys = json.keySet();
		List<SoldItem> list = new ArrayList<SoldItem>();
		for(String key:keys){
			
		}

	}

}
