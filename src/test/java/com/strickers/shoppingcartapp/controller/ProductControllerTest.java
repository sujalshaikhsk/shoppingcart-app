package com.strickers.shoppingcartapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.strickers.shoppingcartapp.dto.BuyRequestDto;
import com.strickers.shoppingcartapp.dto.BuyResponseDto;
import com.strickers.shoppingcartapp.entity.Product;
import com.strickers.shoppingcartapp.exception.ProductNotPresentException;
import com.strickers.shoppingcartapp.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;
	
	BuyRequestDto buyRequestDto=null;
	BuyResponseDto buyResponseDto=null;
	
	@Before
	public void before() {
		buyRequestDto=new BuyRequestDto();
		buyRequestDto.setCreditCardNumber(12434L);
		buyRequestDto.setOtp(2376);
		buyRequestDto.setProductId(1);
		
		buyResponseDto=new BuyResponseDto();
		buyResponseDto.setProductName(1);
		buyResponseDto.setShippingAddress("hassan");
	}


	@Test
	public void testSearchProductByNameForPositive() throws Exception {
		List<Product> productList = new ArrayList<Product>();
		String productName = "SQL";
		Mockito.when(productService.searchProductByName(productName)).thenReturn(productList);
		HttpStatus status = productController.searchProductByName(productName).getStatusCode();
		assertEquals(HttpStatus.OK, status);
	}

	@Test
	public void testSearchProductByNameForNull() throws Exception {
		String productName = null;
		HttpStatus actual = productController.searchProductByName(productName).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, actual);
	}
	
	@Test
	public void testBuyProductForPositive() throws ProductNotPresentException {
		Mockito.when(productService.buyProduct(1L, buyRequestDto)).thenReturn(buyResponseDto);
		Integer response=productController.buyProduct(1L, buyRequestDto).getStatusCodeValue();
		assertEquals(200, response);
	}
	
	@Test
	public void testBuyProductForNegative() throws ProductNotPresentException {
		
		Mockito.when(productService.buyProduct(1L, buyRequestDto)).thenReturn(null);
		Integer response=productController.buyProduct(1L, buyRequestDto).getStatusCodeValue();
		assertEquals(400, response);
		
	}
}
