package com.tech_challenge_fiap.usecases.payment;

import com.tech_challenge_fiap.entities.order.OrderEntity;

public interface PaymentUseCase {
    OrderEntity updateStatus(String orderId, String status);
}
