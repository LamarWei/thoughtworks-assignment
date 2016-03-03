package com.thoughtworks.interview.utils;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.interview.model.Discount;
import com.thoughtworks.interview.model.Item;

public class DiscountCaculatorTest {

	private static Item item;
	
	@BeforeClass
	public static void init(){
		item = new Item("足球", 99.00, "个","运动用品", "ITEM0000001");
	}
	
	@AfterClass
	public static void end(){
		item = null;
	}
	
	@Test
	public void testCaculation(){
		int qty = 3;
		/**
		 * No sale
		 */
		double sub_total = DiscountCaculator.getSubTotal(item, qty);
		double savings = DiscountCaculator.getSavings(item, qty);
		Assert.assertTrue(sub_total == item.getPrice() * qty);
		Assert.assertEquals(Double.parseDouble("0.0"), savings,0);
		/**
		 * Buy two get another same one free
		 */
		item.setDiscount(Discount.BUYTWOGETANOTHERFREE);
		sub_total = DiscountCaculator.getSubTotal(item,qty);
		savings = DiscountCaculator.getSavings(item, qty);
		Assert.assertNotEquals(sub_total, item.getPrice() * qty);
		Assert.assertEquals(item.getPrice()*qty, sub_total + savings,0);
		qty = 5;
		sub_total = DiscountCaculator.getSubTotal(item,qty);
		savings = DiscountCaculator.getSavings(item, qty);
		Assert.assertEquals(sub_total,item.getPrice() * (qty - Math.ceil(qty/3)),0);
		Assert.assertEquals(savings,item.getPrice() * Math.ceil(qty/3),0);
		/**
		 * 5% Off
		 */
		item.setDiscount(Discount.FIVEPERCENTOFF);
		sub_total = DiscountCaculator.getSubTotal(item,qty);
		savings = DiscountCaculator.getSavings(item, qty);
		Assert.assertEquals(sub_total, item.getPrice()*qty*0.95,0);
	}
	
	
}
