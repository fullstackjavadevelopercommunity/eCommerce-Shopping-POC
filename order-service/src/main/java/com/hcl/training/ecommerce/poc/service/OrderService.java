package com.hcl.training.ecommerce.poc.service;

import java.util.List;

import com.hcl.training.ecommerce.poc.model.Order;

/**
 * @author Manjeet Kumar
 *
 *         Sep 21, 2020
 *
 */
public interface OrderService {

	public List<Order> getAllOrder();

	public Order getOrderById(Long orderId) throws Exception;

	public Order saveOrder(Long cartId);

}
