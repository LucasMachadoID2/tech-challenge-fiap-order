package com.tech_challenge_fiap.adapters;

import com.tech_challenge_fiap.dtos.OrderResponseDto;
import com.tech_challenge_fiap.data.models.OrderDataModel;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.util.converter.ClientConverter;
import com.tech_challenge_fiap.util.converter.PaymentConverter;
import com.tech_challenge_fiap.util.converter.ProductConverter;
import lombok.experimental.UtilityClass;

import static java.util.Objects.nonNull;

@UtilityClass
public class OrderAdapter {

    public static OrderEntity entityToOrder(OrderDataModel orderDataModel) {
        return OrderEntity.builder()
                .id(orderDataModel.getId())
                .status(orderDataModel.getStatus())
                .client(nonNull(orderDataModel.getClient()) ? ClientConverter.toDomain(orderDataModel.getClient()) : null)
                .products(orderDataModel.getProducts().stream().map(ProductConverter::toDomain).toList())
                .payment(PaymentConverter.toPaymentDomain(orderDataModel.getPayment()))
                .build();
    }

    public static OrderDataModel orderEntityToDataModel(OrderEntity orderEntity) {
        return OrderDataModel.builder()
                .id(orderEntity.getId())
                .status(orderEntity.getStatus())
                .client(nonNull(orderEntity.getClient()) ? ClientConverter.toEntity(orderEntity.getClient()) : null)
                .products(orderEntity.getProducts().stream().map(ProductConverter::toEntity).toList())
                .payment(PaymentConverter.toEntity(orderEntity.getPayment()))
                .build();
    }

    public static OrderResponseDto toResponse(OrderEntity orderEntity) {
        return OrderResponseDto.builder()
                .id(orderEntity.getId())
                .status(orderEntity.getStatus().name())
                .client(nonNull(orderEntity.getClient()) ? ClientConverter.toResponse(orderEntity.getClient()) : null)
                .products(orderEntity.getProducts().stream().map(ProductConverter::toResponse).toList())
                .payment(PaymentConverter.toResponse(orderEntity.getPayment()))
                .build();
    }
}
