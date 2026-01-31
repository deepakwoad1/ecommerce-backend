package com.vuecart.order_service.service;

import com.vuecart.order_service.client.PaymentClient;
import com.vuecart.order_service.client.ProductClient;
import com.vuecart.order_service.dto.OrderRequest;
import com.vuecart.order_service.dto.PaymentRequest;
import com.vuecart.order_service.dto.PaymentResponse;
import com.vuecart.order_service.entity.Order;
import com.vuecart.order_service.entity.OrderItem;
import com.vuecart.order_service.entity.ShippingAddress;
import com.vuecart.order_service.enums.OrderStatus;
import com.vuecart.order_service.enums.PaymentStatus;
import com.vuecart.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductClient productClient;
	private final PaymentClient paymentClient;

	public OrderService(OrderRepository orderRepository, ProductClient productClient, PaymentClient paymentClient) {
		this.orderRepository = orderRepository;
		this.productClient = productClient;
		this.paymentClient = paymentClient;
	}

	public Order createOrder(OrderRequest request, String userId) {

		Order order = new Order();
		order.setUserId(userId);
		order.setStatus(OrderStatus.CREATED);
		order.setPaymentMethod(request.getPaymentMethod());
		order.setCreatedAt(LocalDateTime.now());

		BigDecimal totalAmount = BigDecimal.ZERO;
		List<OrderItem> items = new ArrayList<>();

		for (var itemReq : request.getItems()) {

			var product = productClient.getProduct(itemReq.getProductId());

			BigDecimal itemTotal = product.price().multiply(BigDecimal.valueOf(itemReq.getQuantity()));

			OrderItem item = new OrderItem();
			item.setProductId(itemReq.getProductId());
			item.setQuantity(itemReq.getQuantity());
			item.setPrice(product.price());
			item.setOrder(order);

			items.add(item);
			totalAmount = totalAmount.add(itemTotal);
		}

		ShippingAddress address = new ShippingAddress();
		address.setName(request.getShippingAddress().getName());
		address.setPhone(request.getShippingAddress().getPhone());
		address.setAddress(request.getShippingAddress().getAddress());
		address.setPincode(request.getShippingAddress().getPincode());
		address.setOrder(order);

		order.setItems(items);
		order.setShippingAddress(address);
		order.setTotalAmount(totalAmount);

		// 1️⃣ Save order first (to get orderId)
		order = orderRepository.save(order);

		// 2️⃣ Call Payment Service (COD)
		PaymentResponse paymentResponse = paymentClient
				.createPayment(new PaymentRequest(order.getId(), totalAmount, request.getPaymentMethod()));

	    // 3️⃣ Update order status using ENUM
	    if (paymentResponse.status() == PaymentStatus.SUCCESS) {
	        order.setStatus(OrderStatus.CONFIRMED);
	    } else {
	        order.setStatus(OrderStatus.PAYMENT_FAILED);
	    }

		// 4️⃣ Save final state
		return orderRepository.save(order);
	}

	public List<Order> getOrdersByUser(String userId) {
		return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
	}
}
