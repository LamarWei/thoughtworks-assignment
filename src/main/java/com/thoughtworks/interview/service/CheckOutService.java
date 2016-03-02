package com.thoughtworks.interview.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.interview.model.Item;

public interface CheckOutService {
	void checkOut(JSONObject json, Map<String, Item> map);
}
