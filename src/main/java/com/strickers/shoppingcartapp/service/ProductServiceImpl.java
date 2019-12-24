package com.strickers.shoppingcartapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.strickers.shoppingcartapp.dto.BuyRequestDto;
import com.strickers.shoppingcartapp.dto.BuyResponseDto;
import com.strickers.shoppingcartapp.dto.TransactionRequestDto;
import com.strickers.shoppingcartapp.entity.Customer;
import com.strickers.shoppingcartapp.entity.Myorder;
import com.strickers.shoppingcartapp.entity.Product;
import com.strickers.shoppingcartapp.exception.ProductNotPresentException;
import com.strickers.shoppingcartapp.repository.CustomerRepository;
import com.strickers.shoppingcartapp.repository.MyOrderRepository;
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
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MyOrderRepository myOrderRepository;

	@Autowired
	private RestTemplate restTemplate;

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

	@Override
	public List<Product> searchProductByName(String productName) throws ProductNotPresentException {
		log.info("Entering into searchProductByName method of service");
		List<Product> products = productRepository.findByProductNameIgnoreCaseStartsWith(productName);
		if (products.isEmpty()) {
			log.error("Exception occured in searchProductByName method:" + ApiConstant.PRODUCTS_NOT_FOUND);
			throw new ProductNotPresentException(ApiConstant.PRODUCTS_NOT_FOUND);
		}
		return products;
	}

	@Override
	public BuyResponseDto buyProduct(Long customerId, BuyRequestDto buyRequestDto) {
		BuyResponseDto buyResponseDto = new BuyResponseDto();
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

		if (optionalCustomer.isPresent() && validateOtp(buyRequestDto)) {
			log.info(" inside validate buy product");
			Optional<Product> product = productRepository.findById(buyRequestDto.getProductId());
			if (product.isPresent()) {
				log.info(" Calling validate product");

				Myorder myorder = new Myorder();
				myorder.setProduct(product.get());
				myorder.setShippingAddress(buyRequestDto.getShippingAddress());
				myorder.setCreditcardNumber(buyRequestDto.getCreditCardNumber());
				myorder.setCustomer(optionalCustomer.get());
				myOrderRepository.save(myorder);
				BeanUtils.copyProperties(buyRequestDto, transactionRequestDto);
				transactionRequestDto.setAmount(product.get().getPrice());
				if (saveTransaction(transactionRequestDto)) {
					BeanUtils.copyProperties(buyRequestDto, buyResponseDto);
					return buyResponseDto;
				}
			}
		}
		return null;
	}

	private Boolean saveTransaction(TransactionRequestDto transactionRequestDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<TransactionRequestDto> entity = new HttpEntity<>(transactionRequestDto, headers);

		String url = "http://localhost:8085/creditcard/creditcards/transactions";

		return restTemplate.exchange(url, HttpMethod.POST, entity, Boolean.class).getBody();

	}

	private boolean validateOtp(BuyRequestDto buyRequestDto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<BuyRequestDto> entity = new HttpEntity<>(buyRequestDto, headers);

		String url = "http://localhost:8085/creditcard/creditcards/otp";

		BuyRequestDto responseOtp = restTemplate.exchange(url, HttpMethod.POST, entity, BuyRequestDto.class).getBody();

		if (responseOtp.getOtp().equals(buyRequestDto.getOtp())) {
			log.info(" OTP matched ");
			return true;
		}
		return false;
	}

}
