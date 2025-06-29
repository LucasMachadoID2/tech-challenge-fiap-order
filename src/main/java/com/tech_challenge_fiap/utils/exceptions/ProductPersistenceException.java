package com.tech_challenge_fiap.utils.exceptions;

public class ProductPersistenceException extends RuntimeException {
    public ProductPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
