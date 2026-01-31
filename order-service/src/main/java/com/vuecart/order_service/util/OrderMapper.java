package com.vuecart.order_service.util;

import com.vuecart.order_service.dto.OrderResponse;
import com.vuecart.order_service.dto.OrderItemResponse;
import com.vuecart.order_service.entity.Order;
import com.vuecart.order_service.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

	private OrderMapper() {
		// utility class
	}

	/*
	 * ========================= Order → OrderResponse =========================
	 */
	public static OrderResponse toOrderResponse(Order order) {

		List<OrderItemResponse> items = order.getItems().stream().map(OrderMapper::toOrderItemResponse)
				.collect(Collectors.toList());
		
		System.out.println(order.getTotalAmount());

		return new OrderResponse(order.getId(), order.getStatus(), order.getTotalAmount(), order.getCreatedAt(), items);
	}

	/*
	 * ========================= OrderItem → OrderItemResponse
	 * =========================
	 */
	private static OrderItemResponse toOrderItemResponse(OrderItem item) {

		return new OrderItemResponse(item.getProductId(), item.getQuantity(), item.getPrice());
	}
}
