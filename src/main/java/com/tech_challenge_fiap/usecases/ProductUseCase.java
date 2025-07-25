package com.tech_challenge_fiap.usecases;

import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.usecases.validator.product.ProductValidator;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;

import java.util.List;

public class ProductUseCase {

    public static ProductEntity create(ProductEntity productEntity, ProductGateway productGateway) {
        return productGateway.save(productEntity);
    }

    public static ProductEntity update(ProductEntity productEntity, ProductGateway productGateway) {
        return productGateway.save(productEntity);
    }

    public static void delete(String id, ProductGateway productGateway) {
        productGateway.deleteById(id);
    }

    public static ProductEntity findById(String id, ProductGateway productGateway) {
        return ProductValidator.findById(productGateway, id);
    }

    public static List<ProductEntity> findAll(ProductGateway productGateway) {
        return productGateway.findAll();
    }

    public static List<ProductEntity> findByCategory(CategoryEnum category, ProductGateway productGateway) {
        return productGateway.findByCategory(category);
    }
}
