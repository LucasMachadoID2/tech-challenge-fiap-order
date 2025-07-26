package com.tech_challenge_fiap.gateways.payment;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;

public interface PaymentGateway {

    PaymentEntity createPayment(OrderEntity orderEntity);
}
