package com.vuecart.order_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuecart.order_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUserIdOrderByCreatedAtDesc(String userId);
}
