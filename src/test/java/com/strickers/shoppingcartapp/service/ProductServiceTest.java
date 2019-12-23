package com.strickers.shoppingcartapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strickers.shoppingcartapp.entity.Product;
import com.strickers.shoppingcartapp.exception.ProductNotPresentException;
import com.strickers.shoppingcartapp.repository.ProductRepository;


@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	@Mock
	ProductRepository productRepository;
	
	@Test
	public void testSearchProductByNameForPositive() throws ProductNotPresentException {
		List<Product> products=new ArrayList<>();
		Product product=new Product();
		product.setCategory("Household");
		product.setProductId(1);
		product.setProductName("Glass");
		products.add(product);
		Mockito.when(productRepository.findByProductNameIgnoreCaseStartsWith("Glass")).thenReturn(products);
		List<Product> productsResult=productServiceImpl.searchProductByName("Glass");
		assertEquals(1, productsResult.size());
	}
	
	@Test(expected = ProductNotPresentException.class)
	public void testSearchProductByNameForNegative() throws ProductNotPresentException {
		List<Product> productsResponse = new ArrayList<>();
		Mockito.when(productRepository.findByProductNameIgnoreCaseStartsWith("Glass")).thenReturn(productsResponse);
		productServiceImpl.searchProductByName("Glass");
	}
}
