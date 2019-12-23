package com.strickers.shoppingcartapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product  {


	@Id
	private Integer productId;
	private String productName;
	private String category;
	private String price;
}
