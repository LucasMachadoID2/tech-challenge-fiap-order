package com.tech_challenge_fiap.repositories.payment;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;

public interface PaymentRepository {
    PaymentEntity createPayment(OrderEntity orderEntity);
}
