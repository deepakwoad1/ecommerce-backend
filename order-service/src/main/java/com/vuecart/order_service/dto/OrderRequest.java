package com.vuecart.order_service.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequest {

    private List<OrderItemRequest> items;
    private ShippingAddressRequest shippingAddress;
    private String paymentMethod;
}

