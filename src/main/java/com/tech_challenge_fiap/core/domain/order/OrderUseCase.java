package com.tech_challenge_fiap.core.domain.order;

import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderRequestDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderUpdateStatusRequestDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentStatusDto;

import java.util.List;

public interface OrderUseCase {

    Order createOrder(OrderRequestDto orderRequestDTO);
    Order updatePaymentStatus(String orderId, PaymentStatusDto paymentStatusDto);
    List<Order> findAll();
    Order updateStatus(OrderUpdateStatusRequestDto orderUpdateStatusRequestDto);
}
