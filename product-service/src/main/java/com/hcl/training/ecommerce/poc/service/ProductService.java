package com.hcl.training.ecommerce.poc.service;

import java.util.List;

import com.hcl.training.ecommerce.poc.model.Product;

/**
 * @author Manjeet Kumar
 *
 *         Sep 1, 2020
 *
 */

public interface ProductService {

	public List<Product> getAllProducts();
	public Product getProductById(Long productId) throws Exception;
	public List<Product> getAllProductByProductCategory(String productCategory) throws Exception;
    public List<Product> getAllProductsByProductName(String productName) throws Exception;
	public Product addProduct(Product product);
	public Product updateProduct(Long productId, Product product);
	public void deleteAllProducts();
	public void deleteProduct(Long productId) throws Exception;
	
}
