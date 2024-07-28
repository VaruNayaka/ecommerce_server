package com.ecommerce.Auno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Auno.exception.OrderException;
import com.ecommerce.Auno.exception.UserException;
import com.ecommerce.Auno.model.Address;
import com.ecommerce.Auno.model.Order;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.service.OrderService;
import com.ecommerce.Auno.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
			@RequestHeader("Authorization") String jwt) throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		Order order = orderService.createOrder(user, shippingAddress);

		return new ResponseEntity<Order>(order, HttpStatus.CREATED);

	}

	@GetMapping("/user")
	public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt)
			throws UserException {
		User user = userService.findUserProfileByJwt(jwt);
		List<Order> orders = orderService.userOrderhistory(user.getId());
		return new ResponseEntity<List<Order>>(orders, HttpStatus.CREATED);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable("orderId") Long orderId,
			@RequestHeader("Authorization") String jwt)

			throws UserException, OrderException {
		User user = userService.findUserProfileByJwt(jwt);
		Order order = orderService.findOrderById(orderId);
		return new ResponseEntity<Order>(order, HttpStatus.ACCEPTED);

	}
}
