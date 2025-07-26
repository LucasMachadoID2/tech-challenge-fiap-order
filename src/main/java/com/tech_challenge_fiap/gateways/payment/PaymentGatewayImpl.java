package com.tech_challenge_fiap.gateways.payment;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentGatewayImpl implements PaymentGateway {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentEntity createPayment(OrderEntity orderEntity) {
        return paymentRepository.createPayment(orderEntity);
    }
}
