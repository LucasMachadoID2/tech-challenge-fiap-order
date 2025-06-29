package com.tech_challenge_fiap.usecases.product;

import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;
import com.tech_challenge_fiap.utils.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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
        ProductEntity product = productGateway.findById(id);

        if (isNull(product)) {
            throw new ProductNotFoundException(id);
        }

        return product;
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
