package com.vuecart.order_service.enums;

public enum OrderStatus {

	CREATED, // Order placed, payment not started
	PAYMENT_PENDING, // Online payment initiated
	CONFIRMED, // Payment success / COD accepted
	PAYMENT_FAILED, // Payment failed
	CANCELLED, // User or system cancelled
	SHIPPED, // Order shipped
	DELIVERED, // Order delivered
	REFUNDED // Refund completed
}
