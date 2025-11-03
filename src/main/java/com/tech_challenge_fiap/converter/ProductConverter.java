package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.product.CategoryEnum;
import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.dtos.internal.ProductResponseDto;
import com.tech_challenge_fiap.entities.ProductEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductConverter {

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
                .productId(entity.getProductId())
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
                .productId(product.getProductId())
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
                .category(product.getCategory().name())
                .quantity(product.getQuantity())
                .productId(product.getProductId())
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
                .category(product.getCategory().name())
                .quantity(product.getQuantity())
                .productId(product.getProductId())
                .build();
    }

    public static Product toDomain(com.tech_challenge_fiap.http.clients.product.response.ProductResponseDto productResponseDto) {
        return Product.builder()
                .name(productResponseDto.getName())
                .description(productResponseDto.getDescription())
                .image(productResponseDto.getImage())
                .price(productResponseDto.getPrice())
                .priceForClient(productResponseDto.getPriceForClient())
                .category(CategoryEnum.valueOf(productResponseDto.getCategory()))
                .quantity(productResponseDto.getQuantity())
                .productId(productResponseDto.getId())
                .build();
    }
}