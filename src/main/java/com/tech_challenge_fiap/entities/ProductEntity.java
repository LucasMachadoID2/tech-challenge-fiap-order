package com.tech_challenge_fiap.entities;

import com.tech_challenge_fiap.domains.product.CategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    private String name;
    private String description;
    private String image;
    private Long price;
    private Long priceForClient;
    private Long quantity;
}