package com.tech_challenge_fiap.gateways.product;

import com.tech_challenge_fiap.dtos.external.ProductDTO;
import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.tech_challenge_fiap.adapters.ProductAdapter.toEntity;

@Component
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository productRepository;

    @Override
    public ProductEntity findById(String id) {
        ProductDTO product = productRepository.findById(id);
        return toEntity(product);
    }
}
