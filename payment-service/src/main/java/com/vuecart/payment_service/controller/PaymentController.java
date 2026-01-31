package com.vuecart.payment_service.controller;

import com.vuecart.payment_service.dto.PaymentRequest;
import com.vuecart.payment_service.dto.PaymentResponse;
import com.vuecart.payment_service.service.PaymentService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse createPayment(
            @RequestBody PaymentRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return paymentService.createPayment(request, jwt.getSubject());
    }
}
