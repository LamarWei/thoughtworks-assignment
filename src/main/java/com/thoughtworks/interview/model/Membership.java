package com.thoughtworks.interview.model;

public enum Membership {
	
	NOMEMBER(1),NORMALMEMBER(0.88),GOLDMEMBER(0.75);

	private double rate;

	private Membership(double rate) {
		this.setRate(rate);
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
}
