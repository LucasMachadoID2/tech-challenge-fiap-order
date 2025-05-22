package com.tech_challenge_fiap.core.domain.product;


import com.tech_challenge_fiap.util.Enum.CategoryEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {
    private String id;
    private String name;
    private String description;
    private String image;
    private Long price;
    private Long priceForClient;
    private CategoryEnum category;
    private Long quantity;
}
