package com.tech_challenge_fiap.usecases;

import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.usecases.context.CreateOrderContext;
import com.tech_challenge_fiap.usecases.validator.product.ProductValidator;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

public class CreateOrderUseCase {

    public static OrderEntity createOrder(CreateOrderContext createOrderContext) {
        ClientEntity clientEntity =
                findClientOrNull(createOrderContext.getClientId(), createOrderContext.getClientGateway());

        List<ProductEntity> productEntities = createOrderContext.getProductIds().stream().map(it ->
                ProductValidator.findById(createOrderContext.getProductGateway(), it)
        ).toList();

        OrderEntity orderEntity = OrderEntity.builder()
                .status(OrderEntityStatusEnum.CREATED)
                .clientEntity(clientEntity)
                .productEntities(productEntities)
                .createdAt(LocalDateTime.now())
                .build();

        PaymentEntity paymentEntity = createOrderContext.getPaymentRepository().createPayment(orderEntity);

        orderEntity.setPaymentEntity(paymentEntity);

        return createOrderContext.getOrderGateway().save(orderEntity);
    }

    private static ClientEntity findClientOrNull(String clientId, ClientGateway clientGateway) {
        if (nonNull(clientId)) {
            return ClientUseCase.findById(clientId, clientGateway);
        }

        return null;
    }
}
