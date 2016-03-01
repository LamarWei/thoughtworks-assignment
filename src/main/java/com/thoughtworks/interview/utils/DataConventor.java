package com.thoughtworks.interview.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;

import com.alibaba.fastjson.JSONObject;

public class DataConventor {
	
	/**
	 * Convert input string to json object
	 * @param str ['itemName','itemName',...]
	 * @return {"itemName":amount,...}
	 */
	public static JSONObject StringtoJson(String str){
		String regex = "'[^']+'";
		Matcher m = Pattern.compile(regex).matcher(str);
		ArrayList<String> al = new ArrayList<String>();
		while(m.find()){
			al.add(m.group(0).replace("'", ""));
		}
		/*
		 * Filter same element
		 */
		HashSet<String> hs=new HashSet<String>(al);  
        Map<String, Object> map = new HashMap<String, Object>();
        for(Iterator<String> it=hs.iterator();it.hasNext();){
        	int count = 0;
        	String key = it.next().toString().replace("'", "");
        	for(int i =0;i<al.size();i++){
        		if(key.equals(al.get(i))){
        			count++;
        		}
        	}
        	if(key.contains("-")){
        		count = NumberUtils.toInt(key.split("-")[1]);
        		key = key.split("-")[0];
        	}
        	map.put(key, count);
        }
        return new JSONObject(map);
	}

}
