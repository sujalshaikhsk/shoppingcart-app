package com.strickers.shoppingcartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.strickers.shoppingcartapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByProductNameIgnoreCaseStartsWith(String productName);

}
