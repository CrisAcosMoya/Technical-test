package com.ecommerce.server.dto;

import lombok.Data;

@Data
public class ProductsDto {

    private String name;

    private String description;

    private Float price;

    private int quantity;

    private String category;
}
