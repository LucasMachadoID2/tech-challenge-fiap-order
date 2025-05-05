package com.tech_challenge_fiap.core.domain.payment;

import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.entrypoint.dto.PaymentResponseDto;

public interface PaymentRepository {

    PaymentResponseDto createPayment(Order order);
}
