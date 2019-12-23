package com.strickers.shoppingcartapp.utils;

/**
 * 
 * @author Sujal
 *
 */
public class ApiConstant {
	private ApiConstant() {
	}

	public static final String SUCCESS = "SUCCESSFUL";
	public static final String FAILED = "FAILED";

	public static final String LOGIN_ERROR = "please enter valid username and password";
	public static final String LOGIN_SUCCESS = "you are successfully logged in";
	public static final String SHOPPING_CART_TYPE = "shopping";
	public static final String ORDER_NOT_FOUND = "no orders found";
	public static final String ORDER_FOUND = "list of orders";

	public static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
	public static final String VALIDATION_FAILED = "VALIDATION FAILED";
	public static final String NO_ELEMENT_FOUND = "NO ELEMENT FOUND";
	public static final String CUSTOMER_NOT_FOUND = "Not a valid customer";
	public static final String POLICY_NOT_FOUND = "No policy found";
	public static final String POLICY_ALREADY_EXISTS = "Policy already exists for the customer";

	public static final Integer SUCCESS_CODE = 200;
	public static final Integer FAILURE_CODE = 404;
	public static final Integer NO_CONTENT_CODE = 204;
	public static final String POLICY_FAILURE = "The customer is unable to buy policy";
	public static final String POLICY_SUCCESS = "The policy is successfully availed";
	public static final String NOT_PERMITTED = "Your age is not permitted to avail the offer";
<<<<<<< HEAD
	public static final String PRODUCTS_NOT_FOUND = "Products is not present";

=======
	public static final String PRODUCTS_NOT_FOUND = "Products are not present";


>>>>>>> 8b9c81477361be4806cb83cae408bd3e0d3ff6cf
}
