package com.tech_challenge_fiap.gateways.product;

import com.tech_challenge_fiap.entities.product.ProductEntity;

public interface ProductGateway {
    ProductEntity findById(String id);
}
