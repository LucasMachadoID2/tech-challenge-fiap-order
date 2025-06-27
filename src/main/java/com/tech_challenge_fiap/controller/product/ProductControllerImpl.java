package com.tech_challenge_fiap.controller.product;

import com.tech_challenge_fiap.adapters.ProductAdapter;
import com.tech_challenge_fiap.dtos.ProductRequestDto;
import com.tech_challenge_fiap.dtos.ProductResponseDto;
import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.usecases.product.ProductUseCase;
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

    private final ProductUseCase productUseCase;

    @Override
    public ProductResponseDto create(ProductRequestDto request) {
        ProductEntity product = productUseCase.create(toEntity(request));
        return toResponse(product);
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto request) {
        ProductEntity productEntity = toEntity(request);
        productEntity.setId(id);
        ProductEntity product = productUseCase.update(productEntity);
        return toResponse(product);
    }

    @Override
    public void delete(String id) {
        productUseCase.delete(id);
    }

    @Override
    public ProductResponseDto findById(String id) {
        ProductEntity product = productUseCase.findById(id);
        return toResponse(product);
    }

    @Override
    public List<ProductResponseDto> findAll() {
        List<ProductEntity> products = productUseCase.findAll();
        return products.stream().map(ProductAdapter::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> findByCategory(CategoryEnum category) {
        List<ProductEntity> products = productUseCase.findByCategory(category);
        return products.stream().map(ProductAdapter::toResponse).collect(Collectors.toList());
    }
}
