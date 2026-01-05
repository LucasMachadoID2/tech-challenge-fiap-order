package com.tech_challenge_fiap.entities;

import com.tech_challenge_fiap.domains.product.CategoryEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    private String name;
    private String description;
    private String image;
    private Long price;
    private Long priceForClient;
    private Long quantity;
    private String productId;
    @Setter
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
}