package com.strickers.shoppingcartapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strickers.shoppingcartapp.dto.ViewOrdersResponseDto;
import com.strickers.shoppingcartapp.entity.Myorder;
import com.strickers.shoppingcartapp.exception.CustomerNotFoundException;
import com.strickers.shoppingcartapp.exception.OrderNotFoundException;
import com.strickers.shoppingcartapp.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {
	
	@InjectMocks
	CustomerController customerController;

	@Mock
	OrderService orderService;
	
	ViewOrdersResponseDto viewOrdersResponseDto=null;
	List<Myorder> orderList = null;
	Myorder myorder = null;
	
	@Before
	public void before() {
		
		orderList = new ArrayList<>();
		myorder = new Myorder();
		myorder.setOrderId(1);
		myorder.setCreditcardNumber(1L);
		orderList.add(myorder);
		
		viewOrdersResponseDto=new ViewOrdersResponseDto();
		viewOrdersResponseDto.setMessage("success");
		viewOrdersResponseDto.setMyorders(orderList);
		
	}
	
	@Test
	public void testViewMyOrdersForPositive() throws OrderNotFoundException, CustomerNotFoundException {
		Mockito.when( orderService.viewMyOrders(1L)).thenReturn(Optional.of(viewOrdersResponseDto));
		Integer response=customerController.viewMyOrders(1L).getStatusCodeValue();
		assertEquals(200, response);
	}
	
	/*
	 * @Test public void testViewMyOrdersForNegative() throws
	 * OrderNotFoundException, CustomerNotFoundException {
	 * Optional<ViewOrdersResponseDto> viewOrdersResponseDtoResponse =
	 * Optional.ofNullable(null); Mockito.when(
	 * orderService.viewMyOrders(1L)).thenReturn(viewOrdersResponseDtoResponse);
	 * Integer response=customerController.viewMyOrders(1L).getStatusCodeValue();
	 * assertEquals(404, response); }
	 */


}
