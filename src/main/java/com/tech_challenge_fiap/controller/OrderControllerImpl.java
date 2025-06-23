package com.tech_challenge_fiap.controller;

import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.dtos.OrderResponseDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentRequestDto;
import com.tech_challenge_fiap.adapters.OrderAdapter;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.usecases.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderControllerImpl implements OrderController {

    private final OrderUseCase orderUseCase;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDTO) {
        var order = orderUseCase.createOrder(orderRequestDTO);
        return OrderAdapter.toResponse(order);
    }

    @Override
    public OrderResponseDto updatePaymentStatus(PaymentRequestDto paymentRequestDto) {
        var order = orderUseCase.updatePaymentStatus(paymentRequestDto);
        return OrderAdapter.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> findAll() {
        var orders = orderUseCase.findAll();
        return orders.stream().map(OrderAdapter::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateStatus(String orderId, OrderEntityStatusEnum status) {
        var order = orderUseCase.updateStatus(orderId, status);
        return OrderAdapter.toResponse(order);
    }
}
