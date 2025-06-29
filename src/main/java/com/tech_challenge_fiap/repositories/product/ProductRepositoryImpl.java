package com.tech_challenge_fiap.repositories.product;

import com.tech_challenge_fiap.data.models.ProductDataModel;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;
import com.tech_challenge_fiap.utils.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoProductRepository mongoRepository;

    @Override
    public ProductDataModel save(ProductDataModel product) {
        try {
            return mongoRepository.save(product);
        } catch (Exception e) {
            throw new ProductPersistenceException("Failed to save product: " + product.getClass().getName(), e);
        }
    }

    @Override
    public void deleteById(String id) {
        if (!mongoRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        try {
            mongoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ProductDeletionException("Failed to delete product with ID: " + id, e);
        }
    }

    @Override
    public Optional<ProductDataModel> findById(String id) {
        return Optional.ofNullable(
                mongoRepository.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException(id))
        );
    }

    @Override
    public List<ProductDataModel> findAll() {
        List<ProductDataModel> products = mongoRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductsNotFoundException();
        }
        return products;
    }

    @Override
    public List<ProductDataModel> findByCategory(CategoryEnum category) {
        List<ProductDataModel> products = mongoRepository.findByCategory(category);
        if (products.isEmpty()) {
            throw new ProductsByCategoryNotFoundException(category);
        }
        return products;
    }
}
