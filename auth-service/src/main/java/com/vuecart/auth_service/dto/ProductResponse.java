package com.vuecart.auth_service.dto;

import java.math.BigDecimal;

public record ProductResponse(Long id, String title, String description, String image, BigDecimal price) {
}
