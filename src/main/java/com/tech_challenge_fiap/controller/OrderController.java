package com.tech_challenge_fiap.controller;

import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.dtos.OrderResponseDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentRequestDto;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;

import java.util.List;

public interface OrderController {

    OrderResponseDto createOrder(OrderRequestDto orderRequestDTO);
    OrderResponseDto updatePaymentStatus(PaymentRequestDto paymentRequestDto);
    List<OrderResponseDto> findAll();
    OrderResponseDto updateStatus(String orderId, OrderEntityStatusEnum status);
}
