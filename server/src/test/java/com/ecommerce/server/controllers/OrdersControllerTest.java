package com.ecommerce.server.controllers;


import com.ecommerce.server.dto.OrderResponse;
import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.service.implementation.OrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class OrdersControllerTest {

    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private OrdersController ordersController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(ordersController).build();
    }

    @Test
    void testCreateOrder() {
        OrdersDto orderDto = new OrdersDto(); // Setup test DTO
        OrderResponse orderResponse = new OrderResponse(); // Setup response

        when(ordersService.createOrder(any(OrdersDto.class))).thenReturn(Mono.just(orderResponse));

        webTestClient.post()
                .uri("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(orderDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderResponse.class)
                .isEqualTo(orderResponse);
    }

    @Test
    void testGetAllOrders() {
        OrdersDto orderDto = new OrdersDto(); // Setup test DTO

        when(ordersService.findAll()).thenReturn(Flux.just(orderDto));

        webTestClient.get()
                .uri("/api/listOrders")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(OrdersDto.class)
                .hasSize(1)
                .contains(orderDto);
    }

    @Test
    void testUpdateOrder() {
        OrdersDto orderDto = new OrdersDto(); // Setup test DTO

        when(ordersService.update(anyLong(), any(OrdersDto.class))).thenReturn(Mono.just(orderDto));

        webTestClient.put()
                .uri("/api/updateOrders/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(orderDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrdersDto.class)
                .isEqualTo(orderDto);
    }

    @Test
    void testDeleteOrder() {
        when(ordersService.delete(anyLong())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/deleteOrders/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Orden con ID 1 eliminada correctamente");
    }
}