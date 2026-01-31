package com.vuecart.auth_service.util;

import com.vuecart.auth_service.dto.ProductResponse;
import com.vuecart.auth_service.entities.Product;

public class ProductMapper {

	public static ProductResponse toResponse(Product product) {
		return new ProductResponse(product.getId(), product.getTitle(), product.getDescription(), product.getImage(),
				product.getPrice());
	}
}
