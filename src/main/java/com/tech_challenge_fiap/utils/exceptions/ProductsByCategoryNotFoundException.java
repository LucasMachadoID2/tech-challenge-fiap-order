package com.tech_challenge_fiap.utils.exceptions;

import com.tech_challenge_fiap.utils.enums.CategoryEnum;

public class ProductsByCategoryNotFoundException extends RuntimeException {
    public ProductsByCategoryNotFoundException(CategoryEnum category) {
        super(String.format("Could not find products for category=%s", category));
    }
}
