package com.ecom.productservice.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecom.productservice.model.entity.Product;
import com.ecom.productservice.model.enums.ProductCategory;
import com.ecom.productservice.model.enums.ProductStatus;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategory(ProductCategory category);

    List<Product> findByBrand(String brand);

    List<Product> findByStatus(ProductStatus status);

    List<Product> findByCategoryAndStatus(ProductCategory category, ProductStatus status);

    List<Product> findByBrandAndStatus(String brand, ProductStatus status);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
}
