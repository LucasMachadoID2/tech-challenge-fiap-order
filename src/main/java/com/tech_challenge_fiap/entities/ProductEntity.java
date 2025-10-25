package com.tech_challenge_fiap.entities;

import com.tech_challenge_fiap.domains.product.CategoryEnum;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Table(name = "product")
public class ProductEntity {

    private Long id;
    private CategoryEnum category;
    private String name;
    private String description;
    private String image;
    private Long price;
    private Long priceForClient;
    private Long quantity;
    private String hasPromotion;
}