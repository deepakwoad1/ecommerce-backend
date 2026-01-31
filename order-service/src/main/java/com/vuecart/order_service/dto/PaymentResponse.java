package com.vuecart.order_service.dto;

import java.util.UUID;

import com.vuecart.order_service.enums.PaymentStatus;

public record PaymentResponse(UUID paymentId, PaymentStatus status) {
}