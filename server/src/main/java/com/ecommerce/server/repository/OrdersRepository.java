package com.ecommerce.server.repository;

import com.ecommerce.server.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
