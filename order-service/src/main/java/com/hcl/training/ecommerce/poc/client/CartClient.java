package com.hcl.training.ecommerce.poc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcl.training.ecommerce.poc.model.Cart;

/**
 * @author Manjeet Kumar
 *
 * Sep 21, 2020
 *
 */

@FeignClient(name = "cart-service", url = "http://localhost:8282/")
public interface CartClient {

	@GetMapping(value = "cart-service/cart/{cartId}")
	public Cart getAllItemsFromCart(@PathVariable(value = "cartId")Long cartId);

	
}
