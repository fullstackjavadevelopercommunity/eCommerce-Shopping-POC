package com.hcl.training.ecommerce.poc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.training.ecommerce.poc.client.CartClient;
import com.hcl.training.ecommerce.poc.dao.OrderRepository;
import com.hcl.training.ecommerce.poc.model.Cart;
import com.hcl.training.ecommerce.poc.model.Order;

/**
 * @author Manjeet Kumar
 *
 *         Sep 21, 2020
 *
 */

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartClient cartClient;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAllOrder() {
		List<Order> resultAllOrderList = orderRepository.findAll();
		if (resultAllOrderList.size() > 0) {
			return resultAllOrderList;
		} else {
			return new ArrayList<Order>();
		}
	}
	

	@Override
	public Order getOrderById(Long orderId) throws Exception {
		Optional<Order> resultOrder = orderRepository.findById(orderId);
		System.out.println("resultOrder:::::::::" + resultOrder);
		if (resultOrder.isPresent()) {
			return resultOrder.get();
		} else {
			throw new Exception("No Cart record exist for given id" + orderId);
		}
	}
	
	
	@Override
	public Order saveOrder(Long cartId) {
		Cart allItemsFromCart = cartClient.getAllItemsFromCart(cartId);
		System.out.println("allItemsFromCart:::" + allItemsFromCart);

		Order order = new Order();
		order.setOrderedDate(LocalDate.now());
		order.setStatus("PAYMENT_EXPECTED");
		order.setTotal(allItemsFromCart.getCartTotal());

		System.out.println("order::::" + order);

		Order saveOrder = orderRepository.save(order);
		return saveOrder;

	}

	

}
