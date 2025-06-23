package com.tech_challenge_fiap.usecases.order;

import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentRequestDto;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;

import java.util.List;

public interface OrderUseCase {

    OrderEntity createOrder(OrderRequestDto orderRequestDTO);
    OrderEntity updatePaymentStatus(PaymentRequestDto paymentRequestDto);
    List<OrderEntity> findAll();
    OrderEntity updateStatus(String orderId, OrderEntityStatusEnum status);
}