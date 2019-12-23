package com.strickers.shoppingcartapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.shoppingcartapp.dto.ViewOrdersResponseDto;
import com.strickers.shoppingcartapp.entity.Customer;
import com.strickers.shoppingcartapp.entity.Myorder;
import com.strickers.shoppingcartapp.exception.CustomerNotFoundException;
import com.strickers.shoppingcartapp.exception.OrderNotFoundException;
import com.strickers.shoppingcartapp.repository.CustomerRepository;
import com.strickers.shoppingcartapp.repository.OrderRepository;
import com.strickers.shoppingcartapp.utils.ApiConstant;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Optional<ViewOrdersResponseDto> viewMyOrders(Long customerId) throws OrderNotFoundException, CustomerNotFoundException {
		ViewOrdersResponseDto viewOrdersResponseDto = new ViewOrdersResponseDto();
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (!customer.isPresent()) {
			throw new CustomerNotFoundException(ApiConstant.ORDER_NOT_FOUND);
		}
		List<Myorder> orderList = orderRepository.findByCustomer(customer.get());
		if (orderList.isEmpty()) {
			throw new OrderNotFoundException(ApiConstant.ORDER_NOT_FOUND);
		}
		viewOrdersResponseDto.setMyorders(orderList);
		
		return Optional.of(viewOrdersResponseDto);
	}

}
