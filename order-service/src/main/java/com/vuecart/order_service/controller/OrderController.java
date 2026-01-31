package com.vuecart.order_service.controller;

import com.vuecart.order_service.dto.OrderRequest;
import com.vuecart.order_service.dto.OrderResponse;
import com.vuecart.order_service.entity.Order;
import com.vuecart.order_service.service.OrderService;
import com.vuecart.order_service.util.OrderMapper;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	/*
	 * ========================= CREATE ORDER (CHECKOUT) =========================
	 */
	@PostMapping
	public OrderResponse createOrder(@RequestBody OrderRequest request, @AuthenticationPrincipal Jwt jwt) {
		String userId = jwt.getSubject(); // Keycloak user id

		Order order = orderService.createOrder(request, userId);

		return OrderMapper.toOrderResponse(order);
	}

	/*
	 * ========================= GET MY ORDERS =========================
	 */
	@GetMapping("/my")
	public List<OrderResponse> getMyOrders(@AuthenticationPrincipal Jwt jwt) {
		String userId = jwt.getSubject();

		return orderService.getOrdersByUser(userId).stream().map(OrderMapper::toOrderResponse).toList();
	}
}
