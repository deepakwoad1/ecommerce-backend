package com.vuecart.order_service.client;

import com.vuecart.order_service.dto.ProductPriceResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

	private final RestTemplate restTemplate;

	public ProductClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ProductPriceResponse getProduct(Long productId) {
		return restTemplate.getForObject("http://localhost:8083/api/products/" + productId, ProductPriceResponse.class);
	}
}
