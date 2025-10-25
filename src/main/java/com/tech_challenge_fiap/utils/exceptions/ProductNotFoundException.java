package com.tech_challenge_fiap.utils.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super(String.format("Could not found product for productId=%s", productId));
    }
}