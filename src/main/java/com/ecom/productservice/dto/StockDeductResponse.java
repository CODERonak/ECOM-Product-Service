
package com.ecom.productservice.dto;

public record StockDeductResponse(
    String productId,
    Integer currentStock
) {}
