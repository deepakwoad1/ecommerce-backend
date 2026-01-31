package com.vuecart.order_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.vuecart.order_service.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders", schema = "transaction_schema")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue
	private UUID id;

	private String userId;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	private String paymentMethod;

	private BigDecimal totalAmount;

	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> items;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private ShippingAddress shippingAddress;
}
