package com.tech_challenge_fiap.core.domain.order;

import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderRequestDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentStatusDto;

public interface OrderUseCase {

    Order createOrder(OrderRequestDto orderRequestDTO);
    Order updatePaymentStatus(String orderId, PaymentStatusDto paymentStatusDto);
}
