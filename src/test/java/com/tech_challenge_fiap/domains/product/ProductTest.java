package com.tech_challenge_fiap.domains.product;

import com.tech_challenge_fiap.utils.exceptions.PriceMustBePositiveException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class ProductTest {

    @Test
    void shouldBuildProductWithSuccess() {
        assertDoesNotThrow(() -> Product.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .image("Test")
                .price(1L)
                .priceForClient(null)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(1L)
                .build());
    }

    @Test
    void shouldThrowPriceMustBePositiveExceptionWhenPriceIsNull() {
        assertThrows(PriceMustBePositiveException.class, () -> Product.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .image("Test")
                .price(null)
                .priceForClient(null)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(1L)
                .build());
    }

    @Test
    void shouldThrowPriceMustBePositiveExceptionWhenPriceIsNegative() {
        assertThrows(PriceMustBePositiveException.class, () -> Product.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .image("Test")
                .price(-1L)
                .priceForClient(null)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(1L)
                .build());
    }
}
