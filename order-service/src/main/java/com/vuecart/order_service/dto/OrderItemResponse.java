package com.vuecart.order_service.dto;

import java.math.BigDecimal;

public record OrderItemResponse(Long productId, Integer quantity, BigDecimal price) {
}
