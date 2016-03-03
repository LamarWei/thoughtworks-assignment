package com.thoughtworks.interview.service;

import java.util.List;

import com.thoughtworks.interview.exception.ItemNotExsitException;
import com.thoughtworks.interview.model.Item;

/**
 * Basic item operation services
 * @author WeiWei
 *
 */
public interface ItemService {
	/**
	 * Set discount type
	 * @param itemSerial
	 * @param discount
	 * @return
	 */
	boolean setDiscount(String itemSerial,int discount) throws ItemNotExsitException;
	/**
	 * Get all items
	 * @return
	 */
	List<Item> getItems();
	/**
	 * Get an item by item serial
	 * @param itemSerial
	 * @return
	 * @throws ItemNotExsitException 
	 */
	Item getItem(String itemSerial) throws ItemNotExsitException;
}
