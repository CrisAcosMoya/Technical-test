package com.ecommerce.server.mapper;

import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;

import java.time.LocalDateTime;

public class ProductsMapper {

    public static Products toEntity(ProductsDto dto) {
        Products product = new Products();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(dto.getCategory());
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    public static ProductsDto toDTO(Products product) {
        ProductsDto dto = new ProductsDto();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCategory(product.getCategory());
        dto.setUpdatedAt(LocalDateTime.now());
        return dto;
    }

    public static void updateEntity(Products product, ProductsDto dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(dto.getCategory());
        product.setUpdatedAt(LocalDateTime.now());
    }
}
