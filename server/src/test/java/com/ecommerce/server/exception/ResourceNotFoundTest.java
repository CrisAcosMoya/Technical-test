package com.ecommerce.server.exception;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ResourceNotFoundTest {

    @Test
    void testResourceNotFoundMessage() {
        String resourceName = "Producto";
        String fieldName = "ID";
        Long fieldValue = 1L;

        ResourceNotFound exception = new ResourceNotFound(resourceName, fieldName, fieldValue);

        String expectedMessage = String.format("No se encontro un %s con el valor %s en el campo %s",
                resourceName, fieldName, fieldValue);
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void testResponseStatus() {
        String resourceName = "Producto";
        String fieldName = "ID";
        Long fieldValue = 1L;

        assertThatThrownBy(() -> {
            throw new ResourceNotFound(resourceName, fieldName, fieldValue);
        }).isInstanceOf(ResourceNotFound.class)
                .hasMessageContaining(resourceName)
                .hasMessageContaining(fieldName)
                .hasMessageContaining(String.valueOf(fieldValue))
                .matches(e -> ((ResourceNotFound) e).getClass().isAnnotationPresent(ResponseStatus.class))
                .matches(e -> {
                    ResponseStatus status = ((ResourceNotFound) e).getClass().getAnnotation(ResponseStatus.class);
                    return status.value() == HttpStatus.NOT_FOUND;
                });
    }

}