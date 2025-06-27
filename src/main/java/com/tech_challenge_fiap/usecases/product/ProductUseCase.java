package com.tech_challenge_fiap.usecases.product;

import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;

import java.util.List;

public interface ProductUseCase {
    ProductEntity create(ProductEntity productEntity);

    ProductEntity update(ProductEntity productEntity);

    void delete(String id);

    ProductEntity findById(String id);

    List<ProductEntity> findAll();

    List<ProductEntity> findByCategory(CategoryEnum category);
}
