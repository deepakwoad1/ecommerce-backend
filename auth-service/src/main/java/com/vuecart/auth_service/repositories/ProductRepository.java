package com.vuecart.auth_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuecart.auth_service.entities.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByIdAndStatus(Long id, String status);
}
