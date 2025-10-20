package com.tech_challenge_fiap.repositories.payment;

import com.tech_challenge_fiap.dtos.external.PaymentDTO;
import com.tech_challenge_fiap.entities.order.OrderEntity;

public interface PaymentRepository {
    PaymentDTO createPayment(OrderEntity orderEntity);
}
