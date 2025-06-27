package com.tech_challenge_fiap.controller.product;

import com.tech_challenge_fiap.dtos.ProductRequestDto;
import com.tech_challenge_fiap.dtos.ProductResponseDto;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;

import java.util.List;

public interface ProductController {
    ProductResponseDto create(ProductRequestDto request);

    ProductResponseDto update(String id, ProductRequestDto request);

    void delete(String id);

    ProductResponseDto findById(String id);

    List<ProductResponseDto> findAll();

    List<ProductResponseDto> findByCategory(CategoryEnum category);
}
