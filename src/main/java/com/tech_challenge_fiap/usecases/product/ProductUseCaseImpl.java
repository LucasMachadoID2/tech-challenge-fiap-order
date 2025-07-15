package com.tech_challenge_fiap.usecases.product;

import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.usecases.validator.product.ProductValidator;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductGateway productGateway;

    @Override
    public ProductEntity create(ProductEntity productEntity) {
        return productGateway.save(productEntity);
    }

    @Override
    public ProductEntity update(ProductEntity productEntity) {
        return productGateway.save(productEntity);
    }

    @Override
    public void delete(String id) {
        productGateway.deleteById(id);
    }

    @Override
    public ProductEntity findById(String id) {
        return ProductValidator.findById(productGateway, id);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productGateway.findAll();
    }

    @Override
    public List<ProductEntity> findByCategory(CategoryEnum category) {
        return productGateway.findByCategory(category);
    }
}
