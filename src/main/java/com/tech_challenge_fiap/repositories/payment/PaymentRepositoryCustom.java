package com.tech_challenge_fiap.repositories.payment;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.dtos.external.PaymentDTO;

public interface PaymentRepositoryCustom {
    PaymentDTO createPayment(Order order);
}
