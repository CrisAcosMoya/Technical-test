package com.ecommerce.server.service.implementation;

import com.ecommerce.server.dto.OrderResponse;
import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Orders;
import com.ecommerce.server.entity.Products;
import com.ecommerce.server.exception.ResourceNotFound;
import com.ecommerce.server.mapper.OrdersMapper;
import com.ecommerce.server.repository.OrdersRepository;
import com.ecommerce.server.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


class OrdersServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private OrdersService ordersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrderSuccess() {
        Products product = new Products();
        product.setId(1L);
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setProductId(1L);

        Orders savedOrder = new Orders();
        OrderResponse orderResponse = OrdersMapper.toResponse(savedOrder, product);

        when(productsRepository.findById(1L)).thenReturn(Mono.just(product));
        when(ordersRepository.save(any(Orders.class))).thenReturn(Mono.just(savedOrder));

        StepVerifier.create(ordersService.createOrder(ordersDto))
                .expectNext(orderResponse)
                .verifyComplete();
    }

    @Test
    void testCreateOrderProductNotFound() {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setProductId(1L);

        when(productsRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(ordersService.createOrder(ordersDto))
                .expectErrorMessage("Producto no encontrado")
                .verify();
    }

    @Test
    void testFindAll() {
        Orders order = new Orders();
        OrdersDto orderDto = OrdersMapper.toDTO(order);

        when(ordersRepository.findAll()).thenReturn(Flux.just(order));

        StepVerifier.create(ordersService.findAll())
                .expectNext(orderDto)
                .verifyComplete();
    }

    @Test
    void testUpdateOrderSuccess() {
        Long orderId = 1L;
        Orders existingOrder = new Orders();
        existingOrder.setId(orderId);
        existingOrder.setProductId(1L);
        existingOrder.setQuantity(5);
        existingOrder.setTotalPrice(100.0F);
        existingOrder.setOrderDate(LocalDateTime.now());
        existingOrder.setStatus("UPDATED");

        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setProductId(1L);
        ordersDto.setQuantity(5);
        ordersDto.setTotalPrice(100.0F);
        ordersDto.setStatus("UPDATED");

        Products product = new Products();
        product.setId(1L);

        when(ordersRepository.findById(orderId)).thenReturn(Mono.just(existingOrder));
        when(productsRepository.findById(1L)).thenReturn(Mono.just(product));
        when(ordersRepository.save(any(Orders.class))).thenReturn(Mono.just(existingOrder));

        StepVerifier.create(ordersService.update(orderId, ordersDto))
                .expectNextMatches(dto -> {
                    assertEquals(ordersDto.getProductId(), dto.getProductId());
                    assertEquals(ordersDto.getQuantity(), dto.getQuantity());
                    assertEquals(ordersDto.getTotalPrice(), dto.getTotalPrice());
                    assertEquals(ordersDto.getStatus(), dto.getStatus());
                    return true;
                })
                .verifyComplete();
    }



    @Test
    void testUpdateOrderNotFound() {
        Long orderId = 1L;
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setProductId(1L);

        when(ordersRepository.findById(orderId)).thenReturn(Mono.empty());

        StepVerifier.create(ordersService.update(orderId, ordersDto))
                .expectError(ResourceNotFound.class)
                .verify();
    }

    @Test
    void testUpdateProductNotFound() {
        Long orderId = 1L;
        Orders existingOrder = new Orders();
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setProductId(1L);

        when(ordersRepository.findById(orderId)).thenReturn(Mono.just(existingOrder));
        when(productsRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(ordersService.update(orderId, ordersDto))
                .expectError(ResourceNotFound.class)
                .verify();
    }

    @Test
    void testDeleteOrderSuccess() {
        Long orderId = 1L;
        Orders existingOrder = new Orders();
        existingOrder.setId(orderId);

        when(ordersRepository.findById(orderId)).thenReturn(Mono.just(existingOrder));
        when(ordersRepository.delete(existingOrder)).thenReturn(Mono.empty());

        StepVerifier.create(ordersService.delete(orderId))
                .verifyComplete();
    }

    @Test
    void testDeleteOrderNotFound() {
        Long orderId = 1L;

        when(ordersRepository.findById(orderId)).thenReturn(Mono.empty());

        StepVerifier.create(ordersService.delete(orderId))
                .expectError(ResourceNotFound.class)
                .verify();
    }

}