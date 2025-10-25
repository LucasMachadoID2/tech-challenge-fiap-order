package com.tech_challenge_fiap.repositories.product;

import com.tech_challenge_fiap.domains.product.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    public Product getProductbyId(Long productId) {
        // TODO: request para o microserviço de produtos
        return null;
    }
}
