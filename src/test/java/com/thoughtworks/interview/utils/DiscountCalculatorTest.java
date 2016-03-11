package com.thoughtworks.interview.utils;

import java.lang.reflect.Member;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.interview.model.Discount;
import com.thoughtworks.interview.model.Item;
import com.thoughtworks.interview.model.Membership;

public class DiscountCalculatorTest {

	private static Item item;
	
	@Before
	public void init(){
		item = new Item("足球", 99.00, "个","运动用品", "ITEM0000001");
	}
	
	@After
	public void end(){
		item = null;
	}
	
	@Test
	public void testTwoDiscount() {
		int qty = 3;
		double sub_total;
		double savings;
		item.setDiscount(Discount.ALLSALE);
		/**
		 * If quantity equals or is greater than three, item matches buy two get another one free rule. 
		 */
		sub_total = DiscountCalculator.getSubTotal(item,qty);
		savings = DiscountCalculator.getSavings(item, qty);
		Assert.assertNotEquals(sub_total, item.getPrice() * qty);
		Assert.assertEquals(item.getPrice()*qty, sub_total + savings,0);
		/**
		 * If quantity is less than three, item matches 5% off rule. 
		 */
		qty = 2;
		sub_total = DiscountCalculator.getSubTotal(item,qty);
		savings = DiscountCalculator.getSavings(item, qty);
		Assert.assertEquals(sub_total, item.getPrice()*qty*0.95,0);
	}

	/**
	 * 5% Off
	 */
	@Test
	public void testFivePercentOff() {
		double sub_total;
		double savings;
		int qty = 3;
		item.setDiscount(Discount.FIVEPERCENTOFF);
		sub_total = DiscountCalculator.getSubTotal(item,qty);
		savings = DiscountCalculator.getSavings(item, qty);
		Assert.assertEquals(sub_total, item.getPrice()*qty*0.95,0.001);
		Assert.assertEquals(item.getPrice()*qty*0.05, savings,0.001);
	}
	
	/**
	 * Buy two get another same one free
	 */
	@Test
	public void testBuyTwoGetOneFree() {
		int qty = 3;
		double sub_total;
		double savings;
		item.setDiscount(Discount.BUYTWOGETONEFREE);
		sub_total = DiscountCalculator.getSubTotal(item,qty);
		savings = DiscountCalculator.getSavings(item, qty);
		Assert.assertNotEquals(sub_total, item.getPrice() * qty);
		Assert.assertEquals(item.getPrice()*qty, sub_total + savings,0);
		qty = 5;
		sub_total = DiscountCalculator.getSubTotal(item,qty);
		savings = DiscountCalculator.getSavings(item, qty);
		Assert.assertEquals(sub_total,item.getPrice() * (qty - Math.ceil(qty/3)),0);
		Assert.assertEquals(savings,item.getPrice() * Math.ceil(qty/3),0);
	}

	/**
	 * No sale
	 */
	@Test
	public void testNoDiscount() {
		int qty = 3;
		double sub_total = DiscountCalculator.getSubTotal(item, qty);
		double savings = DiscountCalculator.getSavings(item, qty);
		Assert.assertTrue(sub_total == item.getPrice() * qty);
		Assert.assertEquals(Double.parseDouble("0.0"), savings,0);
	}
	
	@Test
	public void testFivePercentOffWithMembership(){
		//normal membership
		item.setDiscount(Discount.FIVEPERCENTOFF);
		int qty = 3;
		double sub_total = DiscountCalculator.getSubTotal(item, qty, Membership.NORMALMEMBER);
		double savings = DiscountCalculator.getSavings(item, qty, Membership.NORMALMEMBER);
		Assert.assertEquals(261.36, sub_total,1e-6);
		Assert.assertEquals(35.64, savings,1e-6);
		//gold membership
		sub_total = DiscountCalculator.getSubTotal(item, qty, Membership.GOLDMEMBER);
		savings = DiscountCalculator.getSavings(item, qty, Membership.GOLDMEMBER);
		Assert.assertEquals(211.6125, sub_total,1e-6);
		Assert.assertEquals(85.3875, savings,1e-6);
	}
	
	@Test
	public void testBuyTwoGetOneFreeWithMembership(){
		//normal membership
		item.setDiscount(Discount.BUYTWOGETONEFREE);
		int qty = 3;
		double sub_total = DiscountCalculator.getSubTotal(item, qty, Membership.NORMALMEMBER);
		double savings = DiscountCalculator.getSavings(item, qty, Membership.NORMALMEMBER);
		Assert.assertEquals(198, sub_total,1e-6);
		Assert.assertEquals(99, savings,1e-6);
		//gold membership
		sub_total = DiscountCalculator.getSubTotal(item, qty, Membership.GOLDMEMBER);
		savings = DiscountCalculator.getSavings(item, qty, Membership.GOLDMEMBER);
		Assert.assertEquals(148.5, sub_total,1e-6);
		Assert.assertEquals(148.5, savings,1e-6);
	}
	
	
}
