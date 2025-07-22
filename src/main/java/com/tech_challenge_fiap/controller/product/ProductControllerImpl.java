package com.tech_challenge_fiap.controller.product;

import com.tech_challenge_fiap.adapters.ProductAdapter;
import com.tech_challenge_fiap.dtos.ProductRequestDto;
import com.tech_challenge_fiap.dtos.ProductResponseDto;
import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.usecases.ProductUseCase;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.tech_challenge_fiap.adapters.ProductAdapter.toEntity;
import static com.tech_challenge_fiap.adapters.ProductAdapter.toResponse;

@Component
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductGateway productGateway;

    @Override
    public ProductResponseDto create(ProductRequestDto request) {
        ProductEntity product = ProductUseCase.create(toEntity(request), productGateway);
        return toResponse(product);
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto request) {
        ProductEntity productEntity = toEntity(request);
        productEntity.setId(id);
        ProductEntity product = ProductUseCase.update(productEntity, productGateway);
        return toResponse(product);
    }

    @Override
    public void delete(String id) {
        ProductUseCase.delete(id, productGateway);
    }

    @Override
    public ProductResponseDto findById(String id) {
        ProductEntity product = ProductUseCase.findById(id, productGateway);
        return toResponse(product);
    }

    @Override
    public List<ProductResponseDto> findAll() {
        List<ProductEntity> products = ProductUseCase.findAll(productGateway);
        return products.stream().map(ProductAdapter::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> findByCategory(CategoryEnum category) {
        List<ProductEntity> products = ProductUseCase.findByCategory(category, productGateway);
        return products.stream().map(ProductAdapter::toResponse).collect(Collectors.toList());
    }
}
