package com.ecom.productservice.service;

import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.productservice.dto.*;
import com.ecom.productservice.exception.custom.ProductNotFoundException;
import com.ecom.productservice.mapper.ProductMapper;
import com.ecom.productservice.model.entity.Product;
import com.ecom.productservice.model.enums.ProductCategory;
import com.ecom.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByPrices(BigDecimal min, BigDecimal max) {
        List<Product> products = productRepository.findByPriceBetween(min, max);

        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    // Called by Order Service via OpenFeign
    public boolean deductStock(StockDeductRequest req) {
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if (product.getStockQuantity() < req.getQuantity()) {
            return false; // insufficient stock
        }
        product.setStockQuantity(product.getStockQuantity() - req.getQuantity());
        productRepository.save(product);
        return true;
    }

}
