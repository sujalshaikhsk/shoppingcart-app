package com.strickers.shoppingcartapp.dto;

import java.util.List;

import com.strickers.shoppingcartapp.entity.Myorder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewOrdersResponseDto {
	private Integer statusCode;
	private String message;
	private List<Myorder> myorders;
}
