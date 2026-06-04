package com.ecom.productservice.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

import com.ecom.productservice.model.enums.ProductCategory;
import com.ecom.productservice.model.enums.ProductStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Product description is required")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotNull(message = "Category is required")
    private ProductCategory category;

    @NotNull(message = "Status is required")
    private ProductStatus status;

    @NotBlank(message = "Brand is required")
    private String brand;
}
