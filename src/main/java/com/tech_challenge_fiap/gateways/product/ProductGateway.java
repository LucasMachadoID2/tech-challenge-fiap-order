package com.tech_challenge_fiap.gateways.product;

import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;

import java.util.List;

public interface ProductGateway {
    ProductEntity save(ProductEntity product);

    void deleteById(String id);

    ProductEntity findById(String id);

    List<ProductEntity> findAll();

    List<ProductEntity> findByCategory(CategoryEnum category);
}
