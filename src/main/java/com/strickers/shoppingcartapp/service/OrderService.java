package com.strickers.shoppingcartapp.service;

import java.util.Optional;

import com.strickers.shoppingcartapp.dto.ViewOrdersResponseDto;
import com.strickers.shoppingcartapp.exception.CustomerNotFoundException;
import com.strickers.shoppingcartapp.exception.OrderNotFoundException;

public interface OrderService {
	Optional<ViewOrdersResponseDto> viewMyOrders(Long customerId) throws OrderNotFoundException, CustomerNotFoundException;
}
