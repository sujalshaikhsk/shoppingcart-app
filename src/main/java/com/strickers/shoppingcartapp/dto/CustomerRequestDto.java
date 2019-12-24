package com.strickers.shoppingcartapp.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRequestDto {
	
	private String status;
	private LocalDate createdDate;

}
