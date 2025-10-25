package com.tech_challenge_fiap.repositories.product;

import com.tech_challenge_fiap.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
