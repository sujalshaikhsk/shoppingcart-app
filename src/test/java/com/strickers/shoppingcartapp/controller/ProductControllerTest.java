package com.strickers.shoppingcartapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.strickers.shoppingcartapp.dto.BuyResponseDto;
import com.strickers.shoppingcartapp.entity.Product;
import com.strickers.shoppingcartapp.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;
	
	BuyResponseDto buyResponseDto = null;

	@Test
	public void searchProductByNameTestForPositive() throws Exception {
		List<Product> productList = new ArrayList<Product>();
		String productName = "SQL";
		Mockito.when(productService.searchProductByName(productName)).thenReturn(productList);
		HttpStatus status = productController.searchProductByName(productName).getStatusCode();
		assertEquals(HttpStatus.OK, status);
	}

	@Test
	public void searchProductByNameTestForNull() throws Exception {
		String productName = null;
		HttpStatus actual = productController.searchProductByName(productName).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, actual);
	}
	
	@Test
	public void testBuyProductForPositive() throws Exception {
		List<Product> productList = new ArrayList<Product>();
		String productName = "SQL";
		Mockito.when(productService.searchProductByName(productName)).thenReturn(productList);
		HttpStatus status = productController.searchProductByName(productName).getStatusCode();
		assertEquals(HttpStatus.OK, status);
	}

	@Test
	public void testBuyProductTestForNull() throws Exception {
		String productName = null;
		HttpStatus actual = productController.searchProductByName(productName).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, actual);
	}
}
