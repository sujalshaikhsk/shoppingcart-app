package com.strickers.shoppingcartapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strickers.shoppingcartapp.entity.Customer;
import com.strickers.shoppingcartapp.entity.Myorder;

public interface OrderRepository extends JpaRepository<Myorder, Integer> {

	List<Myorder> findByCustomer(Customer customer);
}
