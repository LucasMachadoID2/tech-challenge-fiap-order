package com.tech_challenge_fiap.core.domain.payment;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentRequestDto;

public interface PaymentUseCase {
    OrderEntity updatePaymentStatus(PaymentRequestDto paymentRequestDto);
}
