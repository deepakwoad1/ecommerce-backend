package com.vuecart.order_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.vuecart.order_service.enums.OrderStatus;

public record OrderResponse(UUID orderId, OrderStatus status, BigDecimal totalAmount, LocalDateTime createdAt,
		List<OrderItemResponse> items) {
}
