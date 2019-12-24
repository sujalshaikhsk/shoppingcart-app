package com.strickers.shoppingcartapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingcartAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartAppApplication.class, args);
	}
}
