package com.strickers.shoppingcartapp.controller;

import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strickers.shoppingcartapp.dto.LoginRequestDto;
import com.strickers.shoppingcartapp.dto.LoginResponseDto;
import com.strickers.shoppingcartapp.dto.ViewOrdersResponseDto;
import com.strickers.shoppingcartapp.exception.OrderNotFoundException;
import com.strickers.shoppingcartapp.service.LoginService;
import com.strickers.shoppingcartapp.service.OrderService;
import com.strickers.shoppingcartapp.utils.ApiConstant;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/creditcards")
@RestController
@Slf4j
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class CustomerController {
	@Autowired
	LoginService shoppingcartloginService;
	@Autowired
	OrderService orderService;

	@PostMapping("/login")
	public ResponseEntity<Optional<LoginResponseDto>> creditCardLogin(@RequestBody LoginRequestDto loginRequestdto)
			throws LoginException {
		log.info("Entering into login method of creditCardLogin in CreditCardController");
		Optional<LoginResponseDto> loginResponsedto = shoppingcartloginService.login(loginRequestdto);
		if (!loginResponsedto.isPresent()) {
			LoginResponseDto loginResponse = new LoginResponseDto();
			loginResponse.setMessage(ApiConstant.LOGIN_ERROR);
			loginResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			log.error("loginResponsedto not presen in redit card login method");
			return new ResponseEntity<>(Optional.of(loginResponse), HttpStatus.NOT_FOUND);
		}
		loginResponsedto.get().setMessage(ApiConstant.LOGIN_SUCCESS);
		loginResponsedto.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(loginResponsedto, HttpStatus.OK);

	}

	@GetMapping("/{customerId}/orders")
	ResponseEntity<Optional<ViewOrdersResponseDto>> viewMyOrders(@PathVariable Long customerId)
			throws OrderNotFoundException {
		Optional<ViewOrdersResponseDto> favouriteBeneficiariesResponseDto = orderService.viewMyOrders(customerId);
		if (favouriteBeneficiariesResponseDto.isPresent()) {
			favouriteBeneficiariesResponseDto.get().setStatusCode(ApiConstant.FAILURE_CODE);
			favouriteBeneficiariesResponseDto.get().setMessage(ApiConstant.ORDER_NOT_FOUND);
			return new ResponseEntity<>(favouriteBeneficiariesResponseDto, HttpStatus.OK);
		}
		ViewOrdersResponseDto viewOrdersResponseDto = new ViewOrdersResponseDto();
		viewOrdersResponseDto.setStatusCode(ApiConstant.SUCCESS_CODE);
		viewOrdersResponseDto.setMessage(ApiConstant.ORDER_FOUND);
		return new ResponseEntity<>(Optional.of(viewOrdersResponseDto), HttpStatus.OK);
	}
}
