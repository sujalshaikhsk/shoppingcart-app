package com.strickers.shoppingcartapp.exception;

public class ProductNotPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductNotPresentException(String message) {
		super(message);
	}

}
