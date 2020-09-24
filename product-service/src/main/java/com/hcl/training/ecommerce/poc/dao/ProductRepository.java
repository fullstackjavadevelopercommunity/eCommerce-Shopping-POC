package com.hcl.training.ecommerce.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.training.ecommerce.poc.model.Product;

/**
 * @author Manjeet Kumar
 *
 *         Sep 1, 2020
 *
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findByProductId(Long productId);

	public List<Product> findAllByProductCategory(String productCategory);

	public List<Product> findAllByProductName(String productName);
	

}
