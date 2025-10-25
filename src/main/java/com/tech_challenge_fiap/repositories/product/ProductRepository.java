package com.tech_challenge_fiap.repositories.product;

import com.tech_challenge_fiap.domains.product.Product;

public interface ProductRepository {

    Product getProductbyId(Long productId);
}
