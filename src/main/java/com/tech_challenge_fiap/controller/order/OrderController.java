package com.tech_challenge_fiap.controller.order;

import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.dtos.OrderResponseDto;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;

import java.util.List;

public interface OrderController {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDTO);

    List<OrderResponseDto> findAll();

    OrderResponseDto updateStatus(String orderId, OrderEntityStatusEnum status);
}
