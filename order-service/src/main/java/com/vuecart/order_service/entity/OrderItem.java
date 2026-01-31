package com.vuecart.order_service.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items", schema = "transaction_schema")
@Getter
@Setter
public class OrderItem {

	@Id
	@GeneratedValue
	private UUID id;

	private Long productId;
	private Integer quantity;
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
