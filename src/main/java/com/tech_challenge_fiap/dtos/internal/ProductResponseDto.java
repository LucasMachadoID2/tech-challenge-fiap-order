package com.tech_challenge_fiap.dtos.internal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tech_challenge_fiap.domains.product.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductResponseDto {
    private Long id;

    private String name;

    private String description;

    private String image;

    private Long price;

    private Long priceForClient;

    private String category;

    private Long quantity;
}
