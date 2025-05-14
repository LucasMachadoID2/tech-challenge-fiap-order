package com.tech_challenge_fiap.adapter.service.inbound.handler;

import com.tech_challenge_fiap.util.exception.*;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<Object> buildResponse(String message, HttpStatus status) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", status.value());
        error.put("error", status.getReasonPhrase());
        error.put("message", message);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(ProductPersistenceException.class)
    public ResponseEntity<Object> handleProductPersistence(ProductPersistenceException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductDeletionException.class)
    public ResponseEntity<Object> handleProductDeletion(ProductDeletionException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseEntity<Object> handleProductsNotFound(ProductsNotFoundException ex) {
        return buildResponse("No products found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductsByCategoryNotFoundException.class)
    public ResponseEntity<Object> handleProductsByCategoryNotFound(ProductsByCategoryNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
