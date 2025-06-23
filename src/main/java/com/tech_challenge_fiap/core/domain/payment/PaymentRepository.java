package com.tech_challenge_fiap.core.domain.payment;

import com.tech_challenge_fiap.entities.order.OrderEntity;

public interface PaymentRepository {

    Payment createPayment(OrderEntity orderEntity);
}
