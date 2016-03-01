package com.thoughtworks.interview.exception;

public class ItemNotExsitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6908756668840994884L;
	
	public ItemNotExsitException(){
		super();
	}
	
	public ItemNotExsitException(String msg){
		super(msg);
	}

}
