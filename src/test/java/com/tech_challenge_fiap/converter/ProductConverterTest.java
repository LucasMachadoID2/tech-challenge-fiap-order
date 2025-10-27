package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.product.CategoryEnum;
import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ProductConverterTest {

    @Test
    void shouldConvertToDomainFromEntity() {
        var entity = ProductEntity.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var product = ProductConverter.toDomain(entity);

        assertEquals(entity.getId(), product.getId());
        assertEquals(entity.getName(), product.getName());
        assertEquals(entity.getDescription(), product.getDescription());
        assertEquals(entity.getImage(), product.getImage());
        assertEquals(entity.getPrice(), product.getPrice());
        assertEquals(entity.getPriceForClient(), product.getPriceForClient());
        assertEquals(entity.getCategory(), product.getCategory());
        assertEquals(entity.getQuantity(), product.getQuantity());
    }

    @Test
    void shouldConvertToEntityFromDomain() {
        var product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var entity = ProductConverter.toEntity(product);

        assertEquals(product.getId(), entity.getId());
        assertEquals(product.getName(), entity.getName());
        assertEquals(product.getDescription(), entity.getDescription());
        assertEquals(product.getImage(), entity.getImage());
        assertEquals(product.getPrice(), entity.getPrice());
        assertEquals(product.getPriceForClient(), entity.getPriceForClient());
        assertEquals(product.getCategory(), entity.getCategory());
        assertEquals(product.getQuantity(), entity.getQuantity());
    }

    @Test
    void shouldConvertToResponseFromDomain() {
        var product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var response = ProductConverter.toResponse(product);

        assertEquals(product.getId(), response.getId());
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getDescription(), response.getDescription());
        assertEquals(product.getImage(), response.getImage());
        assertEquals(product.getPrice(), response.getPrice());
        assertEquals(product.getPriceForClient(), response.getPriceForClient());
        assertEquals(product.getCategory().name(), response.getCategory());
        assertEquals(product.getQuantity(), response.getQuantity());
    }

    @Test
    void shouldConvertToResponseFromEntity() {
        var entity = ProductEntity.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var response = ProductConverter.toResponse(entity);

        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getName(), response.getName());
        assertEquals(entity.getDescription(), response.getDescription());
        assertEquals(entity.getImage(), response.getImage());
        assertEquals(entity.getPrice(), response.getPrice());
        assertEquals(entity.getPriceForClient(), response.getPriceForClient());
        assertEquals(entity.getCategory().name(), response.getCategory());
        assertEquals(entity.getQuantity(), response.getQuantity());
    }
}
