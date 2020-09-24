package com.hcl.training.ecommerce.poc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcl.training.ecommerce.poc.model.Product;

/**
 * @author Manjeet Kumar
 *
 *         Sep 20, 2020
 *
 */

//http://localhost:8181/product-service/products

@FeignClient(name = "product-service", url = "http://localhost:8181/")
public interface ProductClient {

	@GetMapping(value = "product-service/products/{productId}")
	public Product getProductById(@PathVariable(value = "productId") Long productId);

}