package com.tech_challenge_fiap.services.order;

import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.dtos.internal.OrderRequestDto;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDTO);

    List<OrderResponseDto> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();

    OrderResponseDto updateStatus(Long orderId, OrderStatusEnum status);

    void updatePaymentStatus(Long paymentId, PaymentStatusEnum status);
}
