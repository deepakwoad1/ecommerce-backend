package com.vuecart.order_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
	private Long productId;
	private Integer quantity;
}
