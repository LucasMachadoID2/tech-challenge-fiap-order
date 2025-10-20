package com.tech_challenge_fiap.repositories.product;

import com.tech_challenge_fiap.dtos.external.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public ProductDTO findById(String id) {
        try {
            log.debug("Finding product by ID: {}", id);
            return new ProductDTO();
        } catch (Exception e) {
            log.error("Error finding product by ID: {}", id, e);
            throw new RuntimeException("Failed to find product with ID: " + id, e);
        }
    }

}