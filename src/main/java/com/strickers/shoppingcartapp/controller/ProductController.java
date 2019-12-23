package com.strickers.shoppingcartapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strickers.shoppingcartapp.dto.BuyRequestDto;
import com.strickers.shoppingcartapp.dto.BuyResponseDto;
import com.strickers.shoppingcartapp.entity.Product;
import com.strickers.shoppingcartapp.exception.ProductNotPresentException;
import com.strickers.shoppingcartapp.service.ProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * The ProductController class implements the method to search the product by
 * name.
 *
 * @author Bindushree
 * @since 2019-12-23
 */

@RestController
@Slf4j
@RequestMapping("/products")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * This method is to search the product by name and obtaining the list of
	 * products.
	 * 
	 * @author Bindushree
	 * @param productName
	 * @return List<Product> returns a list of products containing that partial
	 *         name.
	 * @throws ProductNotPresentException
	 */

	@GetMapping("")
	public ResponseEntity<List<Product>> searchProductByName(@RequestParam String productName)
			throws ProductNotPresentException {
		log.info("Entering into searchProductByName method of controller");
		if (productName == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			List<Product> products = productService.searchProductByName(productName);
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
	}

	@PostMapping("/buy")
	public ResponseEntity<BuyResponseDto> buyProduct(@RequestBody BuyRequestDto buyRequestDto)
			throws ProductNotPresentException {
		log.info("Entering into searchProductByName method of controller");

		BuyResponseDto buyResponseDto = productService.buyProduct(buyRequestDto);
		return new ResponseEntity<>(buyResponseDto, HttpStatus.OK);

	}

}
