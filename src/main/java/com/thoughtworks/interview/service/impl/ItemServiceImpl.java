package com.thoughtworks.interview.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.interview.exception.ItemNotExsitException;
import com.thoughtworks.interview.model.Item;
import com.thoughtworks.interview.service.ItemService;

public class ItemServiceImpl implements ItemService {
	
	private static Map<String,Item> DEFAULT_ITEM_MAP;
	
	static{
		DEFAULT_ITEM_MAP.put("ITEM0000001",new Item("足球", 99.00, "运动用品", "ITEM0000001"));
		DEFAULT_ITEM_MAP.put("ITEM0000002",new Item("篮球", 99.00, "运动用品", "ITEM0000002"));
		DEFAULT_ITEM_MAP.put("ITEM0000003",new Item("羽毛球", 5.00, "运动用品", "ITEM0000003"));
		DEFAULT_ITEM_MAP.put("ITEM0000004",new Item("乒乓球", 4.00, "运动用品", "ITEM0000004"));
		DEFAULT_ITEM_MAP.put("ITEM0000005",new Item("足球鞋", 300.00, "运动鞋类", "ITEM0000005"));
		DEFAULT_ITEM_MAP.put("ITEM0000006",new Item("篮球鞋", 400.00, "运动鞋类", "ITEM0000006"));
		DEFAULT_ITEM_MAP.put("ITEM0000007",new Item("羽毛球鞋", 500.00, "运动鞋类", "ITEM0000007"));
		DEFAULT_ITEM_MAP.put("ITEM0000008",new Item("乒乓球鞋", 300.00, "运动鞋类", "ITEM0000008"));
		DEFAULT_ITEM_MAP.put("ITEM0000009",new Item("羽毛球球拍", 650.00, "运动用品", "ITEM0000009"));
		DEFAULT_ITEM_MAP.put("ITEM0000010",new Item("乒乓球球拍", 99.00, "运动用品", "ITEM0000010"));
	}

	@Override
	public boolean setDiscount(String itemSerial, int discount) throws ItemNotExsitException {
		if(!DEFAULT_ITEM_MAP.containsKey(itemSerial)){
			throw new ItemNotExsitException();
		}
		Item item = DEFAULT_ITEM_MAP.get(itemSerial);
		item.setDiscount(discount);
		DEFAULT_ITEM_MAP.put(itemSerial, item);
		return true;
	}

	@Override
	public List<Item> getItems() {
		List<Item> items = new ArrayList<Item>();
		for(String key:DEFAULT_ITEM_MAP.keySet()){
			items.add(DEFAULT_ITEM_MAP.get(key));
		}
		return items;
	}

	@Override
	public Item getItem(String itemSerial) throws ItemNotExsitException {
		if(!DEFAULT_ITEM_MAP.containsKey(itemSerial)){
			throw new ItemNotExsitException();
		}
		return null;
	}

}