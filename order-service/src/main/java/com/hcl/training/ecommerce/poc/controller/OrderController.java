package com.hcl.training.ecommerce.poc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.training.ecommerce.poc.model.Cart;
import com.hcl.training.ecommerce.poc.model.Order;
import com.hcl.training.ecommerce.poc.service.OrderService;

/**
 * @author Manjeet Kumar
 *
 *         Sep 21, 2020
 *
 */

@RestController
@RequestMapping("/order-service")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getAllOrderList() {
		log.info("Fetching All Oder list {}");
		List<Order> resultOrderList = orderService.getAllOrder();
		if (resultOrderList.isEmpty()) {
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Order>>(resultOrderList, HttpStatus.OK);

	}

	@GetMapping(value = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> getCartById(@PathVariable("orderId") Long orderId) throws Exception {
		log.info("Fetching Order by orderId {}", orderId);
		Order resultorder = orderService.getOrderById(orderId);
		if (resultorder == null) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(resultorder, HttpStatus.OK);
	}
	
	@PostMapping(value = "/order",params =  "cartId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Order> saveOrder(@RequestParam("cartId") Long cartId) {
		log.info("save the order : {}");	
		Order saveOrder = orderService.saveOrder(cartId);
			return new ResponseEntity<Order>(saveOrder, HttpStatus.CREATED);
	}
}
