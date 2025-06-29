package com.tech_challenge_fiap.repositories.product;

import com.tech_challenge_fiap.data.models.ProductDataModel;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    ProductDataModel save(ProductDataModel product);

    void deleteById(String id);

    Optional<ProductDataModel> findById(String id);

    List<ProductDataModel> findAll();

    List<ProductDataModel> findByCategory(CategoryEnum category);
}
