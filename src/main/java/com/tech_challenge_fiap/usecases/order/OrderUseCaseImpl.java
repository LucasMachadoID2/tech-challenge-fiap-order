package com.tech_challenge_fiap.usecases.order;

import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.entities.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.entities.product.ProductEntity;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.usecases.client.ClientUseCase;
import com.tech_challenge_fiap.usecases.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderGateway orderGateway;
    private final ClientUseCase clientUseCase;
    private final ProductUseCase productUseCase;
    private final PaymentRepository paymentRepository;

    @Override
    public OrderEntity createOrder(String clientId, List<String> productIds) {
        ClientEntity clientEntity = findClientOrNull(clientId);

        List<ProductEntity> productEntities = productIds.stream().map(productUseCase::findById).toList();

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
    public OrderEntity updatePaymentStatus(String orderId, String status) {
        var order = orderGateway.getOrderById(orderId);

        PaymentStatusEnum paymentStatusEnum = PaymentStatusEnum.safeValueOf(status);
        order.getPaymentEntity().setStatus(paymentStatusEnum);

        return orderGateway.save(order);
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
            return clientUseCase.findById(clientId);
        }

        return null;
    }
}
