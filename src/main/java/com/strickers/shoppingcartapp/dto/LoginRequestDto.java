package com.strickers.shoppingcartapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

	private Long mobileNumber;
	private String password;
}
