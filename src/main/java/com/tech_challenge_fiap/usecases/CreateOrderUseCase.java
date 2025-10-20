package com.tech_challenge_fiap.usecases;

import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.usecases.context.CreateOrderContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Slf4j
public class CreateOrderUseCase {

    public static OrderEntity createOrder(CreateOrderContext createOrderContext) {
        ClientEntity clientEntity =
                findClientOrNull(createOrderContext.getClientId(), createOrderContext.getClientGateway());

        List<ProductEntity> productEntities = createOrderContext.getProductIds().stream().map(it ->
                createOrderContext.getProductGateway().findById(it)
        ).toList();

        OrderEntity orderEntity = OrderEntity.builder()
                .id(UUID.randomUUID().toString())
                .status(OrderEntityStatusEnum.CREATED)
                .clientEntity(clientEntity)
                .productEntities(productEntities)
                .createdAt(LocalDateTime.now())
                .build();

        log.info("[UseCase] Recebido DTO: id={}", 
             orderEntity.getId());        
        PaymentEntity paymentEntity = createOrderContext.getPaymentGateway().createPayment(orderEntity);

        orderEntity.setPaymentEntity(paymentEntity);

        return createOrderContext.getOrderGateway().save(orderEntity);
    }

    private static ClientEntity findClientOrNull(String clientId, ClientGateway clientGateway) {
        if (nonNull(clientId)) {
            return clientGateway.findById(clientId);
        }

        return null;
    }
}
