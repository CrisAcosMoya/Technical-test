package com.ecommerce.server.controllers;

import com.ecommerce.server.dto.ErrorResponseDto;
import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;
import com.ecommerce.server.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "REST Para la gestion de productos",
        description = "CRUD REST APIs para la creacion, recuperacion, actualizacion y eliminación de productos"
)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductsController {

    @NonNull
    private ProductsService productsService;

    @Operation(
            summary = "Creacion de producto",
            description = "Permite para crear productos en nuestro sistema de gestión"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Products> createProduct(@RequestBody ProductsDto productDTO) {
        Products createdProduct = productsService.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @Operation(
            summary = "Listar productos",
            description = "Permite listar productos en nuestro sistema de gestión"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping(value = "/listProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductsDto>> getAllProducts() {
        List<ProductsDto> products = productsService.findAll();
        return ResponseEntity.ok(products);
    }

    @Operation(
            summary = "Actualizar producto",
            description = "Permite actualizar productos en nuestro sistema de gestión"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping(value = "/updateProducts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsDto> updateProduct(@PathVariable Long id, @RequestBody ProductsDto productDTO) {
        ProductsDto updatedProduct = productsService.update(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(
            summary = "Eliminar producto",
            description = "Permite eliminar productos en nuestro sistema de gestión"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping(value = "/deleteProducts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productsService.delete(id);
        String message = String.format("Producto con ID %d eliminado correctamente", id);
        return ResponseEntity.ok(message);
    }
}
