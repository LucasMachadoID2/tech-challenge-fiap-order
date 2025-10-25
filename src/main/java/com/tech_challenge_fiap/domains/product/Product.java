package com.tech_challenge_fiap.domains.product;

import com.tech_challenge_fiap.utils.exceptions.PriceMustBePositiveException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Product {
    @Setter
    private Long id;

    private String name;

    private String description;

    private String image;

    private Long price;

    private Long priceForClient;

    private CategoryEnum category;

    private Long quantity;

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

