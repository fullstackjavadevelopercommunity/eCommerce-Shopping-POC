package com.hcl.training.ecommerce.poc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.training.ecommerce.poc.model.Product;
import com.hcl.training.ecommerce.poc.service.ProductService;

/**
 * @author Manjeet Kumar
 *
 *         Aug 31, 2020
 *
 */
//http://localhost:8181/product-service/products
@RestController
@RequestMapping("/product-service")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProductList() {
		log.info("Fetching All Product list {}");
		List<Product> allProducts = productService.getAllProducts();
		if (allProducts.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);

	}

	@GetMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) throws Exception {
		log.info("Fetching Product by id {}", productId);
		Product resultProduct = productService.getProductById(productId);
		if (resultProduct == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(resultProduct, HttpStatus.OK);
	}

	@GetMapping(value = "/products", params = "category", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProductByCategory(@RequestParam("category") String productCategory) throws Exception {
		log.info("Fetching Product by productCategory {}", productCategory);
		List<Product> resultAllProductByCategory = productService.getAllProductByProductCategory(productCategory);
		if (resultAllProductByCategory.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(resultAllProductByCategory, HttpStatus.OK);
	}

	@GetMapping(value = "/products", params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProductsByName(@RequestParam("name") String productName) throws Exception {
		List<Product> resultAllProductsByName = productService.getAllProductsByProductName(productName);
		if (resultAllProductsByName.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(resultAllProductsByName, HttpStatus.OK);
	}

	@PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		log.info("Creating new Product : {}", product);
		Product addProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(addProduct, HttpStatus.CREATED);

	}

	@PutMapping(value = "/products/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
			@RequestBody Product product) {
		log.info("Updating Product with id {}", productId);
		Product updateProduct = productService.updateProduct(productId, product);
		return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);

	}

	@DeleteMapping(value = "/products")
	public ResponseEntity<Product> deleteAllProduct() {
		log.info("Deleting All Products");
		productService.deleteAllProducts();
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId) throws Exception {
		log.info("Fetching & Deleting Product with id {}", productId);
		productService.deleteProduct(productId);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);

	}
}
