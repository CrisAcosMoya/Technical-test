package com.ecommerce.server.dto;

import lombok.Data;

@Data
public class OrdersDto {

    private Long productId;

    private int quantity;

    private Float totalPrice;

    private String status;
}
