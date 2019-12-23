package com.strickers.shoppingcartapp.exception;

public class OrderNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String s) {
		super(s);
	}
}
