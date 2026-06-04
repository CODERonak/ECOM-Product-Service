
package com.ecom.productservice.dto;

import java.math.BigDecimal;

import com.ecom.productservice.model.enums.ProductCategory;
import com.ecom.productservice.model.enums.ProductStatus;

public record ProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        ProductCategory category,
        ProductStatus status,
        String brand) {
}
