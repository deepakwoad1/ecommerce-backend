package com.vuecart.auth_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuecart.auth_service.dto.ProductResponse;
import com.vuecart.auth_service.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ProductResponse getProduct(@PathVariable Long id) {
		return service.getProduct(id);
	}

	@GetMapping
	public List<ProductResponse> getAllProducts() {
		return service.getAllProducts();
	}
}
