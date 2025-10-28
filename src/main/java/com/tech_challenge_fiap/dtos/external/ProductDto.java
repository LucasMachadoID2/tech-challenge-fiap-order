package com.tech_challenge_fiap.dtos.external;

import com.tech_challenge_fiap.domains.product.CategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductDto {

    private String id;

    private String name;

    private String description;

    private String image;

    private Long price;

    private Long priceForClient;

    private CategoryEnum category;

    private Long quantity;
}
