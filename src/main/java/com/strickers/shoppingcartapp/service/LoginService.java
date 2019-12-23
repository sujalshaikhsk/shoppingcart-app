package com.strickers.shoppingcartapp.service;

import java.util.Optional;

import javax.security.auth.login.LoginException;

import com.strickers.shoppingcartapp.dto.LoginRequestDto;
import com.strickers.shoppingcartapp.dto.LoginResponseDto;
import com.strickers.shoppingcartapp.entity.Customer;

public interface LoginService {
	public Optional<LoginResponseDto> login(LoginRequestDto loginRequestdto) throws LoginException;

	public Optional<Customer> saveCustomerDetails(Customer customer);

}
