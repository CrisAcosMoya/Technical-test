package com.ecommerce.server.repository;

import com.ecommerce.server.entity.Orders;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface OrdersRepository extends ReactiveCrudRepository<Orders, Long> {
}
