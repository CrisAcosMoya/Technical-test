package com.ecommerce.server.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductWithoutIdDto {

    private String name;

    private String description;

    private Float price;

    private int quantity;

    private String category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

