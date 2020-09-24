package com.hcl.training.ecommerce.poc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.training.ecommerce.poc.dao.ProductRepository;
import com.hcl.training.ecommerce.poc.model.Product;

/**
 * @author Manjeet Kumar
 *
 *         Sep 1, 2020
 *
 */

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		log.info("--Fetching All Product--service--");
		List<Product> resultFindAllProductList = productRepository.findAll();
		if (resultFindAllProductList.size() > 0) {
			return resultFindAllProductList;
		} else {
			return new ArrayList<Product>();
		}

	}

	@Override
	public Product getProductById(Long productId) throws Exception {
		log.info("--Fetching getProductById--service--");
		Optional<Product> resultFindByProduct = productRepository.findById(productId);
		if (resultFindByProduct.isPresent()) {
			return resultFindByProduct.get();
		} else {
			throw new Exception("No Product record exist for given id" + productId);
		}
	}

	@Override
	public List<Product> getAllProductByProductCategory(String productCategory) throws Exception {
		log.info("--Fetching getProductByproductCategory--service--");
		List<Product> resultFindAllByCategory = productRepository.findAllByProductCategory(productCategory);
		if (!resultFindAllByCategory.isEmpty()) {
			return resultFindAllByCategory;
		} else {
			throw new Exception("No Product record exist for given productCategory" + productCategory);
		}
	}

	@Override
	public List<Product> getAllProductsByProductName(String ProductName) throws Exception {
		log.info("--Fetching getProductByProductName--service--");
		List<Product> resultFindAllByProductName = productRepository.findAllByProductName(ProductName);
		if (!resultFindAllByProductName.isEmpty()) {
			return resultFindAllByProductName;
		} else {
			throw new Exception("No Product record exist for given ProductName" + ProductName);
		}
	}
	
	@Override
	public Product addProduct(Product product) {
		log.info("--Creating addProduct--service--");
		Optional<Product> resultProduct = productRepository.findById(product.getProductId());
		if (!resultProduct.isPresent()) {
			return productRepository.save(product);
		}else {
			return product;
		}
	}

	@Override
	public Product updateProduct(Long productId, Product product) {
		log.info("--Updating updateProduct--service--");
		Optional<Product> resultProduct = productRepository.findById(productId);
		if (!resultProduct.isPresent()) {
			product.setProductId(productId);
		}
	 Product resultUpdatedProduct = productRepository.save(product);
	 return resultUpdatedProduct;

	}

	@Override
	public void deleteAllProducts() {
		log.info("Fetching All Product {}");
		productRepository.deleteAll();
	}

	@Override
	public void deleteProduct(Long productId) throws Exception {
		log.info("Fetching All Product {}");
		Optional<Product> resultProduct = productRepository.findById(productId);
		if (resultProduct.isPresent()) {
		productRepository.deleteById(productId);
		}else {
             throw new Exception("No Product record exist for given id::"+productId);
        }
	}

	

}
