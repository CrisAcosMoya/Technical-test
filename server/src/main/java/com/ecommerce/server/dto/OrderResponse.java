package com.ecommerce.server.dto;

import com.ecommerce.server.entity.Products;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private Long id;

    private Long productId;

    private int quantity;

    private Float totalPrice;

    private LocalDateTime orderDate;

    private String status;

    private Products product;

}