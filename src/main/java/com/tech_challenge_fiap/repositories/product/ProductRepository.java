package com.tech_challenge_fiap.repositories.product;

import com.tech_challenge_fiap.dtos.external.ProductDTO;

public interface ProductRepository {
    ProductDTO findById(String id);
}
