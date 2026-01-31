package com.vuecart.auth_service.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products", schema = "master")
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	private String image;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private String status = "ACTIVE";
}
