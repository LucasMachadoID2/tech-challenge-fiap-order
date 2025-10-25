package com.tech_challenge_fiap.services.product;

import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.entities.ProductEntity;
import com.tech_challenge_fiap.repositories.product.ProductRepository;
import com.tech_challenge_fiap.utils.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tech_challenge_fiap.converter.ProductAdapter.toDomain;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return toDomain(product);
    }
}
