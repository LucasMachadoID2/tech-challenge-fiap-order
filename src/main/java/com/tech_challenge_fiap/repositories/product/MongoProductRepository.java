package com.tech_challenge_fiap.repositories.product;

import com.tech_challenge_fiap.data.models.ProductDataModel;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoProductRepository extends MongoRepository<ProductDataModel, String> {
    List<ProductDataModel> findByCategory(CategoryEnum category);
}

