package com.strickers.shoppingcartapp.service;

import static org.junit.Assert.assertNotNull;

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
import com.strickers.shoppingcartapp.entity.Customer;
import com.strickers.shoppingcartapp.entity.Myorder;
import com.strickers.shoppingcartapp.exception.CustomerNotFoundException;
import com.strickers.shoppingcartapp.exception.OrderNotFoundException;
import com.strickers.shoppingcartapp.repository.CustomerRepository;
import com.strickers.shoppingcartapp.repository.OrderRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {
	@InjectMocks
	OrderServiceImpl orderServiceImpl;

	@Mock
	OrderRepository orderRepository;

	@Mock
	CustomerRepository customerRepository;

	Customer customer = null;
	List<Myorder> orderList = null;
	Myorder myorder = null;

	@Before
	public void before() {
		customer = new Customer();
		customer.setCustomerId(1L);
		customer.setCustomerName("bindu");

		orderList = new ArrayList<>();
		myorder = new Myorder();
		myorder.setCustomer(customer);
		myorder.setOrderId(1);
		myorder.setCreditcardNumber(1L);
		orderList.add(myorder);

	}

	@Test
	public void testViewMyOrdersForPositive() throws OrderNotFoundException, CustomerNotFoundException {
		Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		Mockito.when(orderRepository.findByCustomer(customer)).thenReturn(orderList);
		Optional<ViewOrdersResponseDto> viewOrdersResponseDto = orderServiceImpl.viewMyOrders(1L);
		assertNotNull(viewOrdersResponseDto);
	}

	@Test(expected = CustomerNotFoundException.class)
	public void testViewMyOrdersForNegativeCustomer() throws OrderNotFoundException, CustomerNotFoundException {
		Optional<Customer> customerResponse = Optional.ofNullable(null);
		Mockito.when(customerRepository.findById(1L)).thenReturn(customerResponse);
		orderServiceImpl.viewMyOrders(1L);
	}

	@Test(expected = OrderNotFoundException.class)
	public void testViewMyOrdersForNegativeOrderDetails() throws OrderNotFoundException, CustomerNotFoundException {
		Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		List<Myorder> orderLists = new ArrayList<>();
		Mockito.when(orderRepository.findByCustomer(customer)).thenReturn(orderLists);
		orderServiceImpl.viewMyOrders(1L);
	}

}
