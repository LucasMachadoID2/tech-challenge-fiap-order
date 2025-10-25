package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.entities.ProductEntity;
import com.tech_challenge_fiap.dtos.external.ProductDTO;
import com.tech_challenge_fiap.dtos.internal.ProductRequestDto;
import com.tech_challenge_fiap.dtos.internal.ProductResponseDto;
import com.tech_challenge_fiap.domains.product.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductAdapter {

    public static Product toDomain(ProductEntity entity) {
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .price(entity.getPrice())
                .priceForClient(entity.getPriceForClient())
                .category(entity.getCategory())
                .quantity(entity.getQuantity())
                .build();
    }

    public static ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .priceForClient(product.getPriceForClient())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .build();
    }

    public static ProductResponseDto toResponse(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .priceForClient(product.getPriceForClient())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .build();
    }

    public static ProductResponseDto toResponse(ProductEntity product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .priceForClient(product.getPriceForClient())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .build();
    }
}