package com.tech_challenge_fiap.http.clients.product.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductResponseDto {
    private String id;

    private String name;

    private String description;

    private String image;

    private Long price;

    private Long priceForClient;

    private String category;

    private Long quantity;
}
