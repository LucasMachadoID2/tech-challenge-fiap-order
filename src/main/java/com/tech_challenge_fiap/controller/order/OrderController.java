package com.tech_challenge_fiap.controller.order;

import com.tech_challenge_fiap.dtos.internal.OrderRequestDto;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;

import java.util.List;

public interface OrderController {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDTO);

    List<OrderResponseDto> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();

    OrderResponseDto updateStatus(String orderId, OrderEntityStatusEnum status);
}
