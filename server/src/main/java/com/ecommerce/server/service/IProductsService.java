package com.ecommerce.server.service;

import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;

import java.util.List;

public interface IProductsService {

    Products createProduct(ProductsDto productDTO);

    List<ProductsDto> findAll();

    ProductsDto update(Long id, ProductsDto productDTO);

    void delete(Long id);
}
