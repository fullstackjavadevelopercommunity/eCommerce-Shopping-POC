package com.hcl.training.ecommerce.poc.service;

import java.util.List;

import com.hcl.training.ecommerce.poc.model.Cart;

/**
 * @author Manjeet Kumar
 *
 *         Sep 4, 2020
 *
 */
public interface CartService {

	public List<Cart> getAllCart();

	public Cart getCartById(Long cartId) throws Exception;

	public Cart addItemToCart(Long productId,int quantity) throws Exception;

}
