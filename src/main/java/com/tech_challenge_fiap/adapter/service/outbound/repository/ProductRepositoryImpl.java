package com.tech_challenge_fiap.adapter.service.outbound.repository;

import com.tech_challenge_fiap.adapter.service.outbound.entity.ProductEntity;

import com.tech_challenge_fiap.core.domain.product.ProductRepository;
import com.tech_challenge_fiap.util.Enum.CategoryEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoProductRepository mongoRepository;

    @Override
    public ProductEntity save(ProductEntity product) {
        return mongoRepository.save(product);
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public Optional<ProductEntity> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public List<ProductEntity> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public List<ProductEntity> findByCategory(CategoryEnum category) {
        return mongoRepository.findByCategory(category);
    }
}
