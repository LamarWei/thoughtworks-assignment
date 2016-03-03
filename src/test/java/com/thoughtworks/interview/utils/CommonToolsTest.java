package com.thoughtworks.interview.utils;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class CommonToolsTest {

	@Test
	public void testStringToJson(){
		String str = "['ITEM000001','ITEM000001','ITEM000001', 'ITEM000001', 'ITEM000001', 'ITEM000003-2', 'ITEM000005'," +
				" 'ITEM000005','ITEM000005']";
		String expectStr = "{\"ITEM000005\":3,\"ITEM000003\":2,\"ITEM000001\":5}";
		JSONObject json = JSON.parseObject(expectStr);
		Assert.assertEquals(json, CommonTools.StringtoJson(str));
	}
	
	@Test
	public void testIsExactDivision(){
		double d1 = 2.0;
		double d2 = 2.5;
		Assert.assertTrue(CommonTools.isExactDivision(d1));
		Assert.assertTrue(!CommonTools.isExactDivision(d2));
	}
	
}
