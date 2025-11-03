package com.tech_challenge_fiap.domains.product;

import com.tech_challenge_fiap.utils.exceptions.PriceMustBePositiveException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
public class Product {
    @Setter
    private UUID id;

    private String name;

    private String description;

    private String image;

    private Long price;

    private Long priceForClient;

    private CategoryEnum category;

    private Long quantity;

    private String productId;

    public static ProductBuilder builder() {
        return new CustomProductBuilder();
    }

    private static class CustomProductBuilder extends ProductBuilder {
        @Override
        public Product build() {
            validatePrice();
            return super.build();
        }

        private void validatePrice() {
            if (super.price == null || super.price <= 0) {
                throw new PriceMustBePositiveException();
            }
        }
    }
}

