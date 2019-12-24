package com.strickers.shoppingcartapp.service;

import java.util.List;

import com.strickers.shoppingcartapp.dto.BuyRequestDto;
import com.strickers.shoppingcartapp.dto.BuyResponseDto;
import com.strickers.shoppingcartapp.entity.Product;
import com.strickers.shoppingcartapp.exception.CustomerNotFoundException;
import com.strickers.shoppingcartapp.exception.InvalidOtpException;
import com.strickers.shoppingcartapp.exception.ProductNotPresentException;

public interface ProductService {
	
	List<Product> searchProductByName(String productName) throws ProductNotPresentException;

	BuyResponseDto buyProduct(Long customerId, BuyRequestDto buyRequestDto) throws InvalidOtpException, CustomerNotFoundException ;

}
