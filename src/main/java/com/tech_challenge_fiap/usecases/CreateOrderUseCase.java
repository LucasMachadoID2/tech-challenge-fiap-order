package com.tech_challenge_fiap.usecases;

import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.usecases.validator.product.ProductValidator;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

public class CreateOrderUseCase {

    public static OrderEntity createOrder(String clientId, List<String> productIds, ClientGateway clientGateway,
                                          ProductGateway productGateway, PaymentRepository paymentRepository,
                                          OrderGateway orderGateway) {
        ClientEntity clientEntity = findClientOrNull(clientId, clientGateway);

        List<ProductEntity> productEntities = productIds.stream().map(it ->
                ProductValidator.findById(productGateway, it)
        ).toList();

        OrderEntity orderEntity = OrderEntity.builder()
                .status(OrderEntityStatusEnum.CREATED)
                .clientEntity(clientEntity)
                .productEntities(productEntities)
                .createdAt(LocalDateTime.now())
                .build();

        PaymentEntity paymentEntity = paymentRepository.createPayment(orderEntity);

        orderEntity.setPaymentEntity(paymentEntity);

        return orderGateway.save(orderEntity);
    }

    private static ClientEntity findClientOrNull(String clientId, ClientGateway clientGateway) {
        if (nonNull(clientId)) {
            return ClientUseCase.findById(clientId, clientGateway);
        }

        return null;
    }
}
