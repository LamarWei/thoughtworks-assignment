package com.thoughtworks.interview.model;

public enum Discount {
	NODISCOUNT(1),FIVEPERCENTOFF(0.95),BUYTWOGETONEFREE(0.3),ALLSALE(0);
	
	private double rate;
	
	private Discount(double rate){
		this.setRate(rate);
	}
	
	public static Discount nameOf(String name){
		if(name.toLowerCase().equals("fivepercentoff")){
			return FIVEPERCENTOFF;
		}
		if(name.toLowerCase().equals("buytwogetonefree")){
			return BUYTWOGETONEFREE;
		}
		if(name.toLowerCase().equals("allsale")){
			return ALLSALE;
		}
		return NODISCOUNT;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
