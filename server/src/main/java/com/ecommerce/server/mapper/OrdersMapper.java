package com.ecommerce.server.mapper;

import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.entity.Orders;
import com.ecommerce.server.entity.Products;

public class OrdersMapper {

    public static Orders toEntity(OrdersDto dto, Products product) {
        Orders order = new Orders();
        order.setProduct(product);
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());
        order.setStatus(dto.getStatus());
        return order;
    }

    public static OrdersDto toDTO(Orders order) {
        OrdersDto dto = new OrdersDto();
        dto.setProductId(order.getProduct().getId());
        dto.setQuantity(order.getQuantity());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public static void updateEntity(Orders order, OrdersDto dto, Products product) {
        order.setProduct(product);
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());
        order.setStatus(dto.getStatus());
    }
}

