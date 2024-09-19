package com.ecommerce.server.service.implementation;

import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;
import com.ecommerce.server.exception.ResourceNotFound;
import com.ecommerce.server.mapper.ProductsMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class ProductsServiceTest {

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductsService productsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductSuccess() {
        ProductsDto productDto = new ProductsDto();
        Products product = ProductsMapper.toEntity(productDto);

        when(productsRepository.save(any(Products.class))).thenReturn(Mono.just(product));

        StepVerifier.create(productsService.createProduct(productDto))
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void testFindAllProducts() {
        Products product = new Products();
        product.setUpdatedAt(LocalDateTime.now());
        ProductsDto productDto = ProductsMapper.toDTO(product);

        when(productsRepository.findAll()).thenReturn(Flux.just(product));

        StepVerifier.create(productsService.findAll())
                .assertNext(fetchedProductDto -> {
                    assertEquals(productDto.getName(), fetchedProductDto.getName());
                    assertEquals(productDto.getDescription(), fetchedProductDto.getDescription());
                    assertEquals(productDto.getPrice(), fetchedProductDto.getPrice());
                    assertEquals(productDto.getQuantity(), fetchedProductDto.getQuantity());
                    assertEquals(productDto.getCategory(), fetchedProductDto.getCategory());
                })
                .verifyComplete();
    }


    @Test
    void testUpdateProductSuccess() {
        Long productId = 1L;
        Products existingProduct = new Products();
        existingProduct.setUpdatedAt(LocalDateTime.now());
        ProductsDto productDto = new ProductsDto();

        when(productsRepository.findById(productId)).thenReturn(Mono.just(existingProduct));
        when(productsRepository.save(any(Products.class))).thenReturn(Mono.just(existingProduct));

        StepVerifier.create(productsService.update(productId, productDto))
                .assertNext(updatedProductDto -> {
                    assertEquals(productDto.getName(), updatedProductDto.getName());
                    assertEquals(productDto.getDescription(), updatedProductDto.getDescription());
                    assertEquals(productDto.getPrice(), updatedProductDto.getPrice());
                    assertEquals(productDto.getQuantity(), updatedProductDto.getQuantity());
                    assertEquals(productDto.getCategory(), updatedProductDto.getCategory());
                })
                .verifyComplete();
    }


    @Test
    void testUpdateProductNotFound() {
        Long productId = 1L;
        ProductsDto productDto = new ProductsDto();

        when(productsRepository.findById(productId)).thenReturn(Mono.empty());

        StepVerifier.create(productsService.update(productId, productDto))
                .expectError(ResourceNotFound.class)
                .verify();
    }

    @Test
    void testDeleteProductSuccess() {
        Long productId = 1L;
        Products existingProduct = new Products();

        when(productsRepository.findById(productId)).thenReturn(Mono.just(existingProduct));
        when(productsRepository.delete(existingProduct)).thenReturn(Mono.empty());

        StepVerifier.create(productsService.delete(productId))
                .verifyComplete();
    }

    @Test
    void testDeleteProductNotFound() {
        Long productId = 1L;

        when(productsRepository.findById(productId)).thenReturn(Mono.empty());

        StepVerifier.create(productsService.delete(productId))
                .expectError(ResourceNotFound.class)
                .verify();
    }

}