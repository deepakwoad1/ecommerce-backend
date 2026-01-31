package com.vuecart.order_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(UUID orderId, BigDecimal amount, String method) {
}