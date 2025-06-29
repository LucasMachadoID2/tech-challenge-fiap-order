package com.tech_challenge_fiap.entities.product;

import com.tech_challenge_fiap.utils.enums.CategoryEnum;
import com.tech_challenge_fiap.utils.exceptions.PriceMustBePositiveException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ProductEntity {
    @Setter
    private String id;

    private String name;

    private String description;

    private String image;

    private Long price;

    private Long priceForClient;

    private CategoryEnum category;

    private Long quantity;

    public static ProductEntityBuilder builder() {
        return new CustomProductEntityBuilder();
    }

    private static class CustomProductEntityBuilder extends ProductEntityBuilder {
        @Override
        public ProductEntity build() {
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

