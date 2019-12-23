package com.strickers.shoppingcartapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "myorder")
@Setter
@Getter
@SequenceGenerator(name = "ordersequence", initialValue = 100100)
public class Myorder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersequence")
	private Integer orderId;
	private Long creditcardNumber;

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToOne
<<<<<<< HEAD
	@JoinColumn(name = "product_id")
=======
	@JoinColumn(name = "productId")
>>>>>>> 8b9c81477361be4806cb83cae408bd3e0d3ff6cf
	private Product product;

	private String shippingAddress;
}
