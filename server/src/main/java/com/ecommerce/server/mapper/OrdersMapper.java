package com.ecommerce.server.mapper;

import com.ecommerce.server.dto.OrderResponse;
import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.entity.Orders;
import com.ecommerce.server.entity.Products;

import java.time.LocalDateTime;

public class OrdersMapper {

    public static Orders toEntity(OrdersDto dto, Long productId) {
        Orders order = new Orders();
        order.setProductId(productId);
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(dto.getStatus());
        return order;
    }

    public static OrderResponse toResponse(Orders order, Products product) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setProductId(order.getProductId());
        response.setQuantity(order.getQuantity());
        response.setTotalPrice(order.getTotalPrice());
        response.setOrderDate(order.getOrderDate());
        response.setStatus(order.getStatus());
        response.setProduct(product);
        return response;
    }

    public static OrdersDto toDTO(Orders order) {
        OrdersDto dto = new OrdersDto();
        dto.setProductId(order.getProductId());
        dto.setQuantity(order.getQuantity());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public static void updateEntity(Orders order, OrdersDto dto, Long productId) {
        order.setProductId(productId);
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());
        order.setStatus(dto.getStatus());
    }
}

