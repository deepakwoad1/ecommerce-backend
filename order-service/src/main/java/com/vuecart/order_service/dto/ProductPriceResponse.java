package com.vuecart.order_service.dto;

import java.math.BigDecimal;

public record ProductPriceResponse(Long id, BigDecimal price) {
}
