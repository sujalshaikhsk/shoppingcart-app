package com.strickers.shoppingcartapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strickers.shoppingcartapp.entity.Myorder;

@Repository
public interface MyOrderRepository extends JpaRepository<Myorder, Integer>{

}
