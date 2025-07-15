package com.tech_challenge_fiap.usecases.validator.product;

import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.utils.exceptions.ProductNotFoundException;

import static java.util.Objects.isNull;

public class ProductValidator {

    public static ProductEntity findById(ProductGateway productGateway, String id) {
        ProductEntity product = productGateway.findById(id);

        if (isNull(product)) {
            throw new ProductNotFoundException(id);
        }

        return product;
    }
}
