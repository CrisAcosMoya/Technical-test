package com.ecommerce.server.repository;

import com.ecommerce.server.entity.Products;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductsRepository extends ReactiveCrudRepository<Products, Long> {
}
