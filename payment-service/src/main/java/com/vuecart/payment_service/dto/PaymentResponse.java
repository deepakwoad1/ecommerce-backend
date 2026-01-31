package com.vuecart.payment_service.dto;

import java.util.UUID;

import com.vuecart.payment_service.enums.PaymentStatus;

public record PaymentResponse(UUID paymentId, PaymentStatus status) {
}
