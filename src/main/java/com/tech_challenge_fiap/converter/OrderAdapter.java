package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;
import com.tech_challenge_fiap.entities.OrderEntity;
import lombok.experimental.UtilityClass;

import static java.util.Objects.nonNull;

@UtilityClass
public class OrderAdapter {

    public static Order toDomain(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .status(orderEntity.getStatus())
                .client(
                        nonNull(orderEntity.getClient()) ? ClientAdapter.toDomain(orderEntity.getClient()) : null)
                .productEntities(orderEntity.getProducts().stream().map(ProductAdapter::toDomain).toList())
                .payment(
                        nonNull(orderEntity.getPayment()) ? PaymentAdapter.toDomain(orderEntity.getPayment())
                                : null)
                .createdAt(orderEntity.getCreatedAt())
                .build();
    }

    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .status(order.getStatus())
                .client(nonNull(order.getClient())
                        ? ClientAdapter.toEntity(order.getClient())
                        : null)
                .products(order.getProductEntities().stream().map(ProductAdapter::toEntity).toList())
                .payment(nonNull(order.getPayment())
                        ? PaymentAdapter.toEntity(order.getPayment())
                        : null)
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static OrderResponseDto toResponse(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .client(nonNull(order.getClient()) ? ClientAdapter.toResponse(order.getClient())
                        : null)
                .products(order.getProductEntities().stream().map(ProductAdapter::toResponse).toList())
                .payment(nonNull(order.getPayment())
                        ? PaymentAdapter.toResponse(order.getPayment())
                        : null)
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static OrderResponseDto toResponse(OrderEntity order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .client(nonNull(order.getClient()) ? ClientAdapter.toResponse(order.getClient())
                        : null)
                .products(order.getProducts().stream().map(ProductAdapter::toResponse).toList())
                .payment(nonNull(order.getPayment())
                        ? PaymentAdapter.toResponse(order.getPayment())
                        : null)
                .createdAt(order.getCreatedAt())
                .build();
    }
}