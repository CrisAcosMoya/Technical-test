package com.ecommerce.server.mapper;


import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ProductsMapperTest {

    @Test
    void testToEntity() {
        ProductsDto dto = new ProductsDto();
        dto.setName("Producto de prueba");
        dto.setDescription("Descripción de prueba");
        dto.setPrice(100F);
        dto.setQuantity(10);
        dto.setCategory("Categoría de prueba");

        Products product = ProductsMapper.toEntity(dto);
    }

    @Test
    void testToDTO() {
        Products product = new Products();
        product.setName("Producto 1");
        product.setDescription("Descripción 1");
        product.setPrice(50F);
        product.setQuantity(5);
        product.setCategory("Categoría 1");
        product.setUpdatedAt(LocalDateTime.now());

        ProductsDto dto = ProductsMapper.toDTO(product);
    }

    @Test
    void testUpdateEntity() {
        ProductsDto dto = new ProductsDto();
        dto.setName("Producto Actualizado");
        dto.setDescription("Descripción Actualizada");
        dto.setPrice(150F);
        dto.setQuantity(20);
        dto.setCategory("Categoría Actualizada");

        Products product = new Products();

        ProductsMapper.updateEntity(product, dto);
    }

}