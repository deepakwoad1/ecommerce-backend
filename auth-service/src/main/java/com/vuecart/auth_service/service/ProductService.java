package com.vuecart.auth_service.service;

import org.springframework.stereotype.Service;

import com.vuecart.auth_service.dto.ProductResponse;
import com.vuecart.auth_service.entities.Product;
import com.vuecart.auth_service.repositories.ProductRepository;
import com.vuecart.auth_service.util.ProductMapper;

import java.util.List;

@Service
public class ProductService {

	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public ProductResponse getProduct(Long id) {
		Product product = repository.findByIdAndStatus(id, "ACTIVE")
				.orElseThrow(() -> new RuntimeException("Product not found"));
		return ProductMapper.toResponse(product);
	}

	public List<ProductResponse> getAllProducts() {
		return repository.findAll().stream().filter(p -> "ACTIVE".equals(p.getStatus())).map(ProductMapper::toResponse)
				.toList();
	}
}
