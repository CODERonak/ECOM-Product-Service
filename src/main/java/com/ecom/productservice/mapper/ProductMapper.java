package com.ecom.productservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecom.productservice.dto.ProductRequest;
import com.ecom.productservice.dto.ProductResponse;
import com.ecom.productservice.model.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "produtImg", ignore = true)
    Product toEntity(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);

    List<ProductResponse> toProductResponseList(List<Product> products);
}
