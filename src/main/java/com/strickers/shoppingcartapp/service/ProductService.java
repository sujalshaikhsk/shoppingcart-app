package com.strickers.shoppingcartapp.service;

import java.util.List;

import com.strickers.shoppingcartapp.entity.Product;
import com.strickers.shoppingcartapp.exception.ProductNotPresentException;

public interface ProductService {
	
	
	List<Product> searchProductByName(String productName) throws ProductNotPresentException;

}
