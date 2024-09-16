package com.ecommerce.server.service;

import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;
import com.ecommerce.server.exception.ResourceNotFound;
import com.ecommerce.server.mapper.ProductsMapper;
import com.ecommerce.server.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsService implements IProductsService{


    private ProductsRepository productsRepository;

    @Override
    public Products createProduct(ProductsDto productDTO) {
        Products product = ProductsMapper.toEntity(productDTO);
        return productsRepository.save(product);
    }

    @Override
    public List<ProductsDto> findAll() {
        return productsRepository.findAll().stream()
                .map(ProductsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductsDto update(Long id, ProductsDto productDTO) {
        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Producto no encontrado", "Numero", + id));

        ProductsMapper.updateEntity(product, productDTO);

        Products updatedProduct = productsRepository.save(product);
        return ProductsMapper.toDTO(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Producto no encontrado", "Numero", + id));

        productsRepository.delete(product);
    }
}
