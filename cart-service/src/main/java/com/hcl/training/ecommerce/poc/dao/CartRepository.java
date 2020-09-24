package com.hcl.training.ecommerce.poc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.training.ecommerce.poc.model.Cart;

/**
 * @author Manjeet Kumar
 *
 * Sep 4, 2020
 *
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

		Cart findByCartId(Long cartId);
}
