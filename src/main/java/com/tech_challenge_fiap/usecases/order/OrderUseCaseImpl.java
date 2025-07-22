package com.tech_challenge_fiap.usecases.order;

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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;
    private final ClientGateway clientGateway;
    private final PaymentRepository paymentRepository;

    @Override
    public OrderEntity createOrder(String clientId, List<String> productIds) {
        ClientEntity clientEntity = findClientOrNull(clientId);

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

    @Override
    public List<OrderEntity> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated() {
        return orderGateway.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
    }

    @Override
    public OrderEntity updateStatus(String orderId, OrderEntityStatusEnum status) {
        OrderEntity orderEntityFound = orderGateway.getOrderById(orderId);

        orderEntityFound.setStatus(status);

        return orderGateway.save(orderEntityFound);
    }

    private ClientEntity findClientOrNull(String clientId) {
        if (nonNull(clientId)) {
            return clientGateway.findById(clientId);
        }

        return null;
    }
}
