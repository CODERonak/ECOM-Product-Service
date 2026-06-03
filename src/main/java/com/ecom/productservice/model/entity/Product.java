package com.ecom.productservice.model.entity;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecom.productservice.model.enums.ProductCategory;
import com.ecom.productservice.model.enums.ProductStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    @Indexed
    private String name;

    private String description;

    private BigDecimal price;

    private Integer stockQuantity;

    @Indexed
    private ProductStatus status;

    @Indexed
    private ProductCategory category;

    @Indexed
    private String brand;

    private String produtImg;

    @CreatedDate
    private Instant createdAt;
}
