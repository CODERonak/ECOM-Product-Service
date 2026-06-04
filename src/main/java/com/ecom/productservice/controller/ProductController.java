package com.ecom.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecom.productservice.dto.*;
import com.ecom.productservice.model.enums.ProductCategory;
import com.ecom.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "Operations related to product catalog and inventory")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    @Operation(summary = "Create a product", description = "Adds a new product to the catalog.")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return new ResponseEntity<>(productService.addProduct(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Fetches detailed information about a specific product.")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "List all products", description = "Retrieves all products currently in the system.")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Filter by category", description = "Returns a list of products in the specified category.")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable ProductCategory category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/price-range/{min}/{max}")
    @Operation(summary = "Filter by price", description = "Finds products within a min/max price range.")
    public ResponseEntity<List<ProductResponse>> getProductsByPriceRange(
            @PathVariable BigDecimal min, @PathVariable BigDecimal max) {
        return ResponseEntity.ok(productService.getProductsByPrices(min, max));
    }

    @PostMapping("/deduct-stock")
    @Operation(summary = "Deduct stock", description = "Internal endpoint for Order Service to reduce product inventory.")
    public ResponseEntity<Boolean> deductStock(@RequestBody StockDeductRequest stockDeductRequest) {
        return ResponseEntity.ok(productService.deductStock(stockDeductRequest));
    }
}