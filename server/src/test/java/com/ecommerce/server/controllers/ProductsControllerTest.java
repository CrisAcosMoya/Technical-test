package com.ecommerce.server.controllers;


import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;
import com.ecommerce.server.service.implementation.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ProductsControllerTest {

    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductsController productsController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(productsController).build();
    }

    @Test
    void testCreateProduct() {
        ProductsDto productDto = new ProductsDto();
        Products product = new Products();
        product.setName("Producto de prueba");
        product.setDescription("Descripción de prueba");
        product.setPrice(100F);
        product.setQuantity(10);
        product.setCategory("Categoría de prueba");

        when(productsService.createProduct(any(ProductsDto.class))).thenReturn(Mono.just(product));

        webTestClient.post()
                .uri("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(productDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Products.class)
                .value(responseProduct -> {
                    assertEquals(product.getName(), responseProduct.getName());
                    assertEquals(product.getDescription(), responseProduct.getDescription());
                    assertEquals(product.getPrice(), responseProduct.getPrice());
                    assertEquals(product.getQuantity(), responseProduct.getQuantity());
                    assertEquals(product.getCategory(), responseProduct.getCategory());
                });
    }


    @Test
    void testGetAllProducts() {
        ProductsDto productDto = new ProductsDto();

        when(productsService.findAll()).thenReturn(Flux.just(productDto));

        webTestClient.get()
                .uri("/api/listProducts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductsDto.class)
                .hasSize(1)
                .contains(productDto);
    }

    @Test
    void testUpdateProduct() {
        ProductsDto productDto = new ProductsDto();

        when(productsService.update(anyLong(), any(ProductsDto.class))).thenReturn(Mono.just(productDto));

        webTestClient.put()
                .uri("/api/updateProducts/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(productDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductsDto.class)
                .isEqualTo(productDto);
    }

    @Test
    void testDeleteProduct() {
        when(productsService.delete(anyLong())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/deleteProducts/{id}", 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Producto con ID 1 eliminado correctamente");
    }
}