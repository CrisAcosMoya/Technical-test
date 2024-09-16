package com.ecommerce.server.service;

import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.entity.Orders;
import com.ecommerce.server.entity.Products;
import com.ecommerce.server.exception.ResourceNotFound;
import com.ecommerce.server.mapper.OrdersMapper;
import com.ecommerce.server.repository.OrdersRepository;
import com.ecommerce.server.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdersService implements IOrdersService{

    private OrdersRepository ordersRepository;

    private ProductsRepository productsRepository;

    @Override
    public Orders createOrder(OrdersDto orderDTO) {
        Products product = productsRepository.findById(orderDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Orders order = OrdersMapper.toEntity(orderDTO, product);
        return ordersRepository.save(order);
    }

    @Override
    public List<OrdersDto> findAll() {
        return ordersRepository.findAll().stream()
                .map(OrdersMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersDto update(Long id, OrdersDto orderDTO) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Orden no encontrada", "Numero", + id));

        Products product = productsRepository.findById(orderDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFound("Orden no encontrada", "Numero", +
                        orderDTO.getProductId()));

        OrdersMapper.updateEntity(order, orderDTO, product);

        Orders updatedOrder = ordersRepository.save(order);
        return OrdersMapper.toDTO(updatedOrder);
    }

    @Override
    public void delete(Long id) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Orden no encontrada", "Numero", + id));

        ordersRepository.delete(order);
    }
}
