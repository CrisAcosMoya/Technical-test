package com.ecommerce.server.service.implementation;

import com.ecommerce.server.dto.OrderResponse;
import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.entity.Orders;
import com.ecommerce.server.exception.ResourceNotFound;
import com.ecommerce.server.mapper.OrdersMapper;
import com.ecommerce.server.repository.OrdersRepository;
import com.ecommerce.server.repository.ProductsRepository;
import com.ecommerce.server.service.IOrdersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class OrdersService implements IOrdersService {

    private OrdersRepository ordersRepository;

    private ProductsRepository productsRepository;

    @Override
    public Mono<OrderResponse> createOrder(OrdersDto orderDTO) {
        return productsRepository.findById(orderDTO.getProductId())
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")))
                .flatMap(product -> {
                    Orders order = OrdersMapper.toEntity(orderDTO, product.getId());
                    return ordersRepository.save(order)
                            .map(savedOrder -> OrdersMapper.toResponse(savedOrder, product));
                });
    }


    @Override
    public Flux<OrdersDto> findAll() {
        return ordersRepository.findAll()
                .map(OrdersMapper::toDTO);
    }

    @Override
    public Mono<OrdersDto> update(Long id, OrdersDto orderDTO) {
        return ordersRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFound("Orden no encontrada", "Numero", id)))
                .flatMap(order -> productsRepository.findById(orderDTO.getProductId())
                        .switchIfEmpty(Mono.error(new ResourceNotFound("Producto no encontrado",
                                "Numero", orderDTO.getProductId())))
                        .flatMap(product -> {
                            OrdersMapper.updateEntity(order, orderDTO, product.getId());
                            return ordersRepository.save(order);
                        })
                )
                .map(OrdersMapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return ordersRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFound("Orden no encontrada", "Numero", id)))
                .flatMap(ordersRepository::delete);
    }
}
