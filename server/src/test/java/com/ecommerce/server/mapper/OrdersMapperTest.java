package com.ecommerce.server.mapper;


import com.ecommerce.server.dto.OrderResponse;
import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.entity.Orders;
import com.ecommerce.server.entity.Products;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class OrdersMapperTest {

    @Test
    void testToEntity() {
        OrdersDto dto = new OrdersDto();
        dto.setQuantity(5);
        dto.setTotalPrice(100F);
        dto.setStatus("CREATED");

        Long productId = 1L;

        Orders order = OrdersMapper.toEntity(dto, productId);
    }

    @Test
    void testToResponse() {
        Orders order = new Orders();
        order.setId(1L);
        order.setProductId(2L);
        order.setQuantity(10);
        order.setTotalPrice(200F);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("SHIPPED");

        Products product = new Products();
        product.setName("Producto 1");

        OrderResponse response = OrdersMapper.toResponse(order, product);
    }

    @Test
    void testToDTO() {
        Orders order = new Orders();
        order.setProductId(3L);
        order.setQuantity(7);
        order.setTotalPrice(300F);
        order.setStatus("DELIVERED");

        OrdersDto dto = OrdersMapper.toDTO(order);
    }

    @Test
    void testUpdateEntity() {
        OrdersDto dto = new OrdersDto();
        dto.setQuantity(8);
        dto.setTotalPrice(400F);
        dto.setStatus("UPDATED");

        Orders order = new Orders();
        Long productId = 4L;

        OrdersMapper.updateEntity(order, dto, productId);
    }

}