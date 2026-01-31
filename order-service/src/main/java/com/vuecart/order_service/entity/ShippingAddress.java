package com.vuecart.order_service.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shipping_address", schema = "transaction_schema")
@Getter @Setter
public class ShippingAddress {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String phone;
    private String address;
    private String pincode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}