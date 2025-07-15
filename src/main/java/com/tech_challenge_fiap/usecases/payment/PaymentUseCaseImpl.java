package com.tech_challenge_fiap.usecases.payment;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

    private final OrderGateway orderGateway;

    @Override
    public OrderEntity updateStatus(String orderId, String status) {
        var order = orderGateway.getOrderById(orderId);

        PaymentStatusEnum paymentStatusEnum = PaymentStatusEnum.safeValueOf(status);
        order.getPaymentEntity().setStatus(paymentStatusEnum);

        return orderGateway.save(order);
    }
}
