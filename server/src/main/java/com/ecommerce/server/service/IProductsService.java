package com.ecommerce.server.service;

import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IProductsService {

    Mono<Products> createProduct(ProductsDto productDTO);

    Flux<ProductsDto> findAll();

    Mono<ProductsDto> update(Long id, ProductsDto productDTO);

    Mono<Void> delete(Long id);
}
