package com.tech_challenge_fiap.services.order;

import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.dtos.internal.OrderRequestDto;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDTO);

    List<OrderResponseDto> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();

    OrderResponseDto updateStatus(UUID orderId, OrderStatusEnum status);

    void updatePaymentStatus(String paymentId, PaymentStatusEnum status);
}
