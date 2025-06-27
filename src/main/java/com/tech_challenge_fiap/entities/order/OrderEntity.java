package com.tech_challenge_fiap.entities.order;

import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.utils.exceptions.OrderProductsCantBeNullOrEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

import static java.util.Objects.nonNull;

@Getter
@Setter
@Builder
public class OrderEntity {
    private String id;

    @NonNull
    private OrderEntityStatusEnum status;

    private ClientEntity clientEntity;

    @NonNull
    private List<ProductEntity> productEntities;

    private PaymentEntity paymentEntity;

    public static OrderEntityBuilder builder() {
        return new CustomOrderEntityBuilder();
    }

    public Long getOrderPrice() {
        if(nonNull(this.clientEntity)) {
            return this.productEntities.stream().map(product -> {
                if(nonNull(product.getPriceForClient())) {
                    return product.getPriceForClient();
                }
                return product.getPrice();
            }).mapToLong(Long::longValue).sum();
        }

        return this.productEntities.stream().map(ProductEntity::getPrice).mapToLong(Long::longValue).sum();
    }

    private static class CustomOrderEntityBuilder extends OrderEntityBuilder {
        @Override
        public OrderEntity build() {
            validateProducts();
            return super.build();
        }

        private void validateProducts() {
            if(super.productEntities.isEmpty()) {
                throw new OrderProductsCantBeNullOrEmpty();
            }
        }
    }
}
