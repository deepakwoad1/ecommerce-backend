package com.vuecart.payment_service.service;

import com.vuecart.payment_service.dto.PaymentRequest;
import com.vuecart.payment_service.dto.PaymentResponse;
import com.vuecart.payment_service.entity.Payment;
import com.vuecart.payment_service.enums.PaymentStatus;
import com.vuecart.payment_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;

	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public PaymentResponse createPayment(PaymentRequest request, String userId) {

		Payment payment = new Payment();
		payment.setOrderId(request.orderId());
		payment.setUserId(userId);
		payment.setAmount(request.amount());
		payment.setMethod(request.method());
		payment.setStatus(PaymentStatus.SUCCESS); // COD → always success
		payment.setCreatedAt(LocalDateTime.now());

		paymentRepository.save(payment);

		return new PaymentResponse(payment.getId(), payment.getStatus());
	}
}
