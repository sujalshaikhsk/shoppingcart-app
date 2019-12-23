package com.strickers.shoppingcartapp.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuyResponseDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer productName;
	
	private String shippingAddress;
	
	private String message;
	private Integer statusCode;

}
