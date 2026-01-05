package com.tech_challenge_fiap.cucumber.datatable;

import com.tech_challenge_fiap.domains.product.CategoryEnum;
import com.tech_challenge_fiap.entities.ProductEntity;
import io.cucumber.java.DataTableType;

import java.util.Map;
import java.util.UUID;

import static java.util.Objects.nonNull;

public class ProductDataTableConfig {

    @DataTableType
    public ProductEntity productEntry(Map<String, String> row) {
        return ProductEntity.builder()
                .id(nonNull(row.get("id")) ? UUID.fromString(row.get("id")) : null)
                .category(nonNull(row.get("category")) ? CategoryEnum.valueOf(row.get("category")) : null)
                .name(row.get("name"))
                .description(row.get("description"))
                .image(row.get("image"))
                .price(nonNull(row.get("price")) ? Long.parseLong(row.get("price")) : null)
                .priceForClient(nonNull(row.get("priceForClient")) ? Long.parseLong(row.get("priceForClient")) : null)
                .quantity(nonNull(row.get("quantity")) ? Long.parseLong(row.get("quantity")) : null)
                .productId(row.get("productId"))
                .build();
    }
}
