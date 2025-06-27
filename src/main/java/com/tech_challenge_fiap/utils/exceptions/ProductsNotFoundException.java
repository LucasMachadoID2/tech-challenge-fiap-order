package com.tech_challenge_fiap.utils.exceptions;

public class ProductsNotFoundException extends RuntimeException {
    public ProductsNotFoundException() {
        super("Could not find any product.");
    }
}
