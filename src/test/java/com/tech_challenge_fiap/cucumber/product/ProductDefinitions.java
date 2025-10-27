package com.tech_challenge_fiap.cucumber.product;

import com.tech_challenge_fiap.entities.ProductEntity;
import com.tech_challenge_fiap.repositories.product.ProductRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductDefinitions {

    private final ProductRepository productRepository;

    @Dado("^que exista produtos cadastrados no sistema")
    @Transactional
    public void createProducts(DataTable dataTable) {
        productRepository.saveAll(dataTable.asList(ProductEntity.class));
    }
}
