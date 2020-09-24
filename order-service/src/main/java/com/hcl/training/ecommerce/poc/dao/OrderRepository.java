package com.hcl.training.ecommerce.poc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.training.ecommerce.poc.model.Order;

/**
 * @author Manjeet Kumar
 *
 *         Sep 21, 2020
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
