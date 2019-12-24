package com.strickers.shoppingcartapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strickers.shoppingcartapp.dto.CustomerRequestDto;
import com.strickers.shoppingcartapp.dto.LoginRequestDto;
import com.strickers.shoppingcartapp.dto.LoginResponseDto;
import com.strickers.shoppingcartapp.dto.ViewOrdersResponseDto;
import com.strickers.shoppingcartapp.entity.Customer;
import com.strickers.shoppingcartapp.entity.Myorder;
import com.strickers.shoppingcartapp.exception.CustomerNotFoundException;
import com.strickers.shoppingcartapp.exception.OrderNotFoundException;
import com.strickers.shoppingcartapp.service.LoginService;
import com.strickers.shoppingcartapp.service.OrderService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;

	@Mock
	OrderService orderService;

	@Mock
	LoginService shoppingcartloginService;

	ViewOrdersResponseDto viewOrdersResponseDto = null;
	List<Myorder> orderList = null;
	Myorder myorder = null;
	CustomerRequestDto customerRequestDto = null;
	Customer customer = null;
	 LoginRequestDto loginRequestdto=null;
	 LoginResponseDto loginResponsedto=null;

	@Before
	public void before() {

		orderList = new ArrayList<>();
		myorder = new Myorder();
		myorder.setOrderId(1);
		myorder.setCreditcardNumber(1L);
		orderList.add(myorder);

		viewOrdersResponseDto = new ViewOrdersResponseDto();
		viewOrdersResponseDto.setMessage("success");
		viewOrdersResponseDto.setMyorders(orderList);

		customerRequestDto = new CustomerRequestDto();

		customer = new Customer();
		customer.setAddress("hassan");
		customer.setCustomerName("bindu");
		customer.setCustomerId(1L);
		
		loginRequestdto=new LoginRequestDto();
		loginRequestdto.setMobileNumber(678L);
		loginRequestdto.setPassword("bindu");
		
		loginResponsedto=new LoginResponseDto();
		loginResponsedto.setCustomerId(1L);
		loginResponsedto.setCustomerName("bindu");

	}

	@Test
	public void testViewMyOrdersForPositive() throws OrderNotFoundException, CustomerNotFoundException {
		Mockito.when(orderService.viewMyOrders(1L)).thenReturn(Optional.of(viewOrdersResponseDto));
		Integer response = customerController.viewMyOrders(1L).getStatusCodeValue();
		assertEquals(200, response);
	}

	
	@Test
	public void testSaveCustomerDetails() throws LoginException {
		Mockito.when(shoppingcartloginService.saveCustomerDetails(customerRequestDto))
				.thenReturn(Optional.of(customer));
		Integer response = customerController.saveCustomerDetails(customerRequestDto).getStatusCodeValue();
		assertEquals(200, response);
	}

	@Test
	public void testSaveCustomerDetailsForNegative() throws LoginException {
		Optional<Customer> customerResponse = Optional.ofNullable(null);
		Mockito.when(shoppingcartloginService.saveCustomerDetails(customerRequestDto)).thenReturn(customerResponse);
		Integer response = customerController.saveCustomerDetails(customerRequestDto).getStatusCodeValue();
		assertEquals(404, response);
	}
	
	@Test
	public void testCreditCardLoginForPositive() throws LoginException {
		Mockito.when(shoppingcartloginService.login(loginRequestdto)).thenReturn(Optional.of(loginResponsedto));
		Integer response = customerController.creditCardLogin(loginRequestdto).getStatusCodeValue();
		assertEquals(200, response);
	}

	@Test
	public void testCreditCardLoginForNegative() throws LoginException {
		Optional<LoginResponseDto> loginResponse = Optional.ofNullable(null);
		Mockito.when(shoppingcartloginService.login(loginRequestdto)).thenReturn(loginResponse);
		Integer response = customerController.creditCardLogin(loginRequestdto).getStatusCodeValue();
		assertEquals(404, response);
	}

}
