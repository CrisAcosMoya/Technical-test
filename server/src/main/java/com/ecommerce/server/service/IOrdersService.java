package com.ecommerce.server.service;

import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.entity.Orders;

import java.util.List;

public interface IOrdersService {

    Orders createOrder(OrdersDto orderDTO);

    List<OrdersDto> findAll();

    OrdersDto update(Long id, OrdersDto orderDTO);

    void delete(Long id);
}
