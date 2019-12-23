package com.strickers.shoppingcartapp.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.shoppingcartapp.dto.LoginRequestDto;
import com.strickers.shoppingcartapp.dto.LoginResponseDto;
import com.strickers.shoppingcartapp.entity.Customer;
import com.strickers.shoppingcartapp.repository.CustomerRepository;
import com.strickers.shoppingcartapp.utils.ApiConstant;
import com.strickers.shoppingcartapp.utils.StringConstant;
import com.strickers.shoppingcartapp.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * @Description This method is used for user to login with valid credentials
	 * @param loginRequestdto
	 * @return LoginResponsedto
	 * @exception LOGIN_ERROR
	 */
	public Optional<LoginResponseDto> login(LoginRequestDto loginRequestdto) throws LoginException {
		log.info("Entering into login() method of LoginServiceImpl");
		Optional<Customer> customerResponse = customerRepository
				.findByMobileNumber(loginRequestdto.getMobileNumber());
		if (!customerResponse.isPresent()) {
			log.error("Exception occured in login() method of LoginServiceImpl");
			throw new LoginException(ApiConstant.LOGIN_ERROR);
		}
		
		String decriptedPassword=Utils.decrypt(customerResponse.get().getPassword());
		log.error(loginRequestdto.getPassword()+"  :::   "+decriptedPassword);
		if(!loginRequestdto.getPassword().equalsIgnoreCase(decriptedPassword)) {
			log.error("Exception occured for wrong password in login() method of LoginServiceImpl");
			throw new LoginException(ApiConstant.LOGIN_ERROR);
		}
		
		LoginResponseDto loginResponsedto = new LoginResponseDto();
		loginResponsedto.setCustomerID(customerResponse.get().getCustomerId());
		loginResponsedto.setCustomerName(customerResponse.get().getCustomerName());
		loginResponsedto.setType(ApiConstant.SHOPPING_CART_TYPE);
		return Optional.of(loginResponsedto);
	}
	
	/**
	 * 
	 * @param customer
	 * @return Optional<Customer> 
	 */
	@Override
	public Optional<Customer> saveCustomerDetails(Customer customer) {
		customer.setCreatedDate(LocalDate.now());
		customer.setStatus(StringConstant.ACTIVE_STATUS);
		Customer customer1= customerRepository.save(customer);
		return Optional.ofNullable(customer1);
	}
	
	
}
