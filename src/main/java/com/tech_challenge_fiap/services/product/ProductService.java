package com.tech_challenge_fiap.services.product;

import com.tech_challenge_fiap.domains.product.Product;

public interface ProductService {

    Product findById(Long id);
}
