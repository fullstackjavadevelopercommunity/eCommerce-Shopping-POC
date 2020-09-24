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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.training.ecommerce.poc.model.Cart;
import com.hcl.training.ecommerce.poc.model.CartItem;
import com.hcl.training.ecommerce.poc.model.Product;
import com.hcl.training.ecommerce.poc.service.CartService;

/**
 * @author Manjeet Kumar
 *
 *         Sep 4, 2020
 *
 */
//http://localhost:8282/cart-service/cart
@RestController
@RequestMapping("/cart-service")
public class CartController {

	private static final Logger log = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;

	@GetMapping(value = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cart>> getAllCartList() {
		log.info("Fetching All Cart list {}");
		List<Cart> resultcartList = cartService.getAllCart();
		if (resultcartList.isEmpty()) {
			return new ResponseEntity<List<Cart>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Cart>>(resultcartList, HttpStatus.OK);

	}

	@GetMapping(value = "/cart/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cart> getCartById(@PathVariable("cartId") Long cartId) throws Exception {
		log.info("Fetching Cart by cartId {}", cartId);
		Cart resultcart = cartService.getCartById(cartId);
		if (resultcart == null) {
			return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cart>(resultcart, HttpStatus.OK);
	}

	@PostMapping(value = "/cart", params = { "productId","quantity"}, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private  ResponseEntity<Cart> addItemToCart(@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity) throws Exception {
		log.info("add item into cart : {}");
		Cart resultCart = cartService.addItemToCart(productId,quantity);
		return new ResponseEntity<Cart>(resultCart, HttpStatus.CREATED);
	}

}
