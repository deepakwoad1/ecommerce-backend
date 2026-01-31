package com.vuecart.payment_service.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.vuecart.payment_service.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments", schema = "transaction_schema")
@Getter
@Setter
public class Payment {

	@Id
	@GeneratedValue
	private UUID id;

	private UUID orderId;
	private String userId;
	private BigDecimal amount;
	private String method;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	private LocalDateTime createdAt;
}
