package com.hcl.training.ecommerce.poc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.training.ecommerce.poc.client.ProductClient;
import com.hcl.training.ecommerce.poc.dao.CartRepository;
import com.hcl.training.ecommerce.poc.model.Cart;
import com.hcl.training.ecommerce.poc.model.CartItem;
import com.hcl.training.ecommerce.poc.model.Product;

/**
 * @author Manjeet Kumar
 *
 *         Sep 4, 2020
 *
 */
@Service
//@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductClient productClient;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> getAllCart() {
		List<Cart> resultAllCartList = cartRepository.findAll();
		if (resultAllCartList.size() > 0) {
			return resultAllCartList;
		} else {
			return new ArrayList<Cart>();
		}
	}

	@Override
	public Cart getCartById(Long cartId) throws Exception {
		Optional<Cart> resultCart = cartRepository.findById(cartId);
		System.out.println("resultCart:::::::::" + resultCart);
		if (resultCart.isPresent()) {
			return resultCart.get();
		} else {
			throw new Exception("No Cart record exist for given id" + cartId);
		}
	}

	@Override
	public Cart addItemToCart(Long productId, int quantity) throws Exception {
		Product product = productClient.getProductById(productId);
		System.out.println("product:...." + product);

		float total = 0f;

		List<CartItem> cartItems = new ArrayList<CartItem>();
		CartItem cartItem = new CartItem();
		cartItem.setCartItemName(product.getProductName());
		cartItem.setCartItemPrice(product.getProductPrice());
		cartItems.add(cartItem);

		System.out.println("cartItems::" + cartItems);

		for (CartItem item : cartItems) {
			total += item.getCartItemPrice();
			System.out.println("total::" + total);
		}

		Cart cart = new Cart();
		cart.setCartItems(cartItems);
		cart.setCartTotal(total);
		cart.setQuantity(quantity);
		
		Cart saveCart = cartRepository.save(cart);
		return saveCart;
	}

}
