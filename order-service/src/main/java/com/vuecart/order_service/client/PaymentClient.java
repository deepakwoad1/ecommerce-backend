package com.vuecart.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vuecart.order_service.config.FeignClientConfig;
import com.vuecart.order_service.dto.PaymentRequest;
import com.vuecart.order_service.dto.PaymentResponse;

@FeignClient(name = "payment-service", url = "http://localhost:8084", configuration = FeignClientConfig.class)
public interface PaymentClient {

	@PostMapping("/api/payments")
	PaymentResponse createPayment(@RequestBody PaymentRequest request);
}
