package com.ecommerce.server.controllers;

import com.ecommerce.server.dto.ErrorResponseDto;
import com.ecommerce.server.dto.OrderResponse;
import com.ecommerce.server.dto.OrdersDto;
import com.ecommerce.server.service.implementation.OrdersService;
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
        name = "REST Para la gestion de ordenes",
        description = "CRUD REST APIs para la creacion, recuperacion, actualizacion y eliminación de ordenes"
)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrdersController {

    @NonNull
    private final OrdersService ordersService;

    @Operation(
            summary = "Creacion de orden",
            description = "Permite para crear ordenes en nuestro sistema de gestión"
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
    @PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<OrderResponse>> createOrder(@RequestBody OrdersDto orderDTO) {
        return ordersService.createOrder(orderDTO)
                .map(ResponseEntity::ok);
    }

    @Operation(
            summary = "Listar ordenes",
            description = "Permite listar ordenes en nuestro sistema de gestión"
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
    @GetMapping(value = "/listOrders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<OrdersDto> getAllOrders() {
        return ordersService.findAll();
    }

    @Operation(
            summary = "Actualizar orden",
            description = "Permite actualizar ordenes en nuestro sistema de gestión"
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
    @PutMapping(value = "/updateOrders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<OrdersDto>> updateOrder(@PathVariable Long id, @RequestBody OrdersDto orderDTO) {
        return ordersService.update(id, orderDTO)
                .map(ResponseEntity::ok);
    }

    @Operation(
            summary = "Eliminación de ordenes",
            description = "Permite eliminar ordenes en nuestro sistema de gestión"
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
    @DeleteMapping(value = "/deleteOrders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> deleteOrder(@PathVariable Long id) {
        return ordersService.delete(id)
                .thenReturn(ResponseEntity.ok(String.format("Orden con ID %d eliminada correctamente", id)));
    }
}

