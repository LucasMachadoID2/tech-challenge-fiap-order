package com.tech_challenge_fiap.controller.order;

import com.tech_challenge_fiap.adapters.OrderAdapter;
import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.dtos.OrderResponseDto;
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
        var order = orderUseCase.createOrder(orderRequestDTO.getClientId(), orderRequestDTO.getProductIds());
        return OrderAdapter.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated() {
        var orders = orderUseCase.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
        return orders.stream().map(OrderAdapter::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateStatus(String orderId, OrderEntityStatusEnum status) {
        var order = orderUseCase.updateStatus(orderId, status);
        return OrderAdapter.toResponse(order);
    }
}
