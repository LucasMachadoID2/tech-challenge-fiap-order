package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;
import com.tech_challenge_fiap.entities.OrderEntity;
import lombok.experimental.UtilityClass;

import static java.util.Objects.nonNull;

@UtilityClass
public class OrderConverter {

    public static Order toDomain(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .status(orderEntity.getStatus())
                .client(
                        nonNull(orderEntity.getClient()) ? ClientConverter.toDomain(orderEntity.getClient()) : null)
                .productEntities(orderEntity.getProducts().stream().map(ProductConverter::toDomain).toList())
                .payment(
                        nonNull(orderEntity.getPayment()) ? PaymentConverter.toDomain(orderEntity.getPayment())
                                : null)
                .createdAt(orderEntity.getCreatedAt())
                .build();
    }

    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .status(order.getStatus())
                .client(nonNull(order.getClient())
                        ? ClientConverter.toEntity(order.getClient())
                        : null)
                .products(order.getProductEntities().stream().map(ProductConverter::toEntity).toList())
                .payment(nonNull(order.getPayment())
                        ? PaymentConverter.toEntity(order.getPayment())
                        : null)
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static OrderResponseDto toResponse(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .client(nonNull(order.getClient()) ? ClientConverter.toResponse(order.getClient())
                        : null)
                .products(order.getProductEntities().stream().map(ProductConverter::toResponse).toList())
                .payment(nonNull(order.getPayment())
                        ? PaymentConverter.toResponse(order.getPayment())
                        : null)
                .createdAt(order.getCreatedAt())
                .build();
    }

    public static OrderResponseDto toResponse(OrderEntity order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .client(nonNull(order.getClient()) ? ClientConverter.toResponse(order.getClient())
                        : null)
                .products(order.getProducts().stream().map(ProductConverter::toResponse).toList())
                .payment(nonNull(order.getPayment())
                        ? PaymentConverter.toResponse(order.getPayment())
                        : null)
                .createdAt(order.getCreatedAt())
                .build();
    }
}