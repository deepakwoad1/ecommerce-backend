package com.vuecart.order_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuecart.order_service.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
