package com.ecommerce.server.service;

import com.ecommerce.server.dto.OrderResponse;
import com.ecommerce.server.dto.OrdersDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IOrdersService {

    Mono<OrderResponse> createOrder(OrdersDto orderDTO);

    Flux<OrdersDto> findAll();

    Mono<OrdersDto> update(Long id, OrdersDto orderDTO);

    Mono<Void> delete(Long id);
}
