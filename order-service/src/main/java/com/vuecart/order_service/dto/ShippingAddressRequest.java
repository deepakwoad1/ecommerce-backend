package com.vuecart.order_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingAddressRequest {
	private String name;
	private String phone;
	private String address;
	private String pincode;
}
