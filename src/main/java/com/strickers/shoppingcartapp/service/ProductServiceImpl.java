package com.strickers.shoppingcartapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.shoppingcartapp.entity.Product;
import com.strickers.shoppingcartapp.exception.ProductNotPresentException;
import com.strickers.shoppingcartapp.repository.ProductRepository;
import com.strickers.shoppingcartapp.utils.ApiConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * The ProductController class implements the method to search the product by
 * name.
 *
 * @author Bindushree
 * @since 2019-12-23
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	/**
	 * This method is to search the product by
	 * name and obtaining the list of products.
	 * 
	 * @author Bindushree
	 * @param productName
	 * @return List<Product> returns a list of products containing that partial name.
	 * @throws ProductNotPresentException
	 */
	
	@Override
	public List<Product> searchProductByName(String productName) throws ProductNotPresentException {
		log.info("Entering into searchProductByName method of service");
		List<Product> products=productRepository.findByProductNameIgnoreCaseStartsWith(productName);
		if(products.isEmpty()) {
			log.error("Exception occured in searchProductByName method:"+ApiConstant.PRODUCTS_NOT_FOUND);
			throw new ProductNotPresentException(ApiConstant.PRODUCTS_NOT_FOUND );
		}
		return products;
	}

}
