package com.tech_challenge_fiap.domains.order;

import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.utils.exceptions.OrderProductsCantBeNullOrEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Getter
@Setter
@Builder
public class Order {
    private UUID id;

    @NonNull
    private OrderStatusEnum status;

    private Client client;

    private List<Product> products;

    private Payment payment;

    @NonNull
    private LocalDateTime createdAt;

    public static OrderBuilder builder() {
        return new CustomOrderBuilder();
    }

    public Long getOrderPrice() {
        if (nonNull(this.client)) {
            return this.products.stream().map(product -> {
                if (nonNull(product.getPriceForClient())) {
                    return product.getPriceForClient();
                }
                return product.getPrice();
            }).mapToLong(Long::longValue).sum();
        }
        return this.products.stream().map(Product::getPrice).mapToLong(Long::longValue).sum();
    }

    private static class CustomOrderBuilder extends OrderBuilder {
        @Override
        public Order build() {
            validateProducts();

            return super.build();
        }

        private void validateProducts() {
            if (isNull(super.products) || super.products.isEmpty()) {
                throw new OrderProductsCantBeNullOrEmpty();
            }
        }
    }
}
