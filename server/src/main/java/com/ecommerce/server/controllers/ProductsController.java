package com.ecommerce.server.controllers;

import com.ecommerce.server.dto.ErrorResponseDto;
import com.ecommerce.server.dto.ProductsDto;
import com.ecommerce.server.entity.Products;
import com.ecommerce.server.service.implementation.ProductsService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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
    private final ProductsService productsService;

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
    public Mono<ResponseEntity<Products>> createProduct(@RequestBody ProductsDto productDTO) {
        return productsService.createProduct(productDTO)
                .map(ResponseEntity::ok);
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
    public Flux<ProductsDto> getAllProducts() {
        return productsService.findAll();
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
    public Mono<ResponseEntity<ProductsDto>> updateProduct(@PathVariable Long id, @RequestBody ProductsDto productDTO) {
        return productsService.update(id, productDTO)
                .map(ResponseEntity::ok);
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
    public Mono<ResponseEntity<String>> deleteProduct(@PathVariable Long id) {
        return productsService.delete(id)
                .thenReturn(ResponseEntity.ok(String.format("Producto con ID %d eliminado correctamente", id)));
    }
}

