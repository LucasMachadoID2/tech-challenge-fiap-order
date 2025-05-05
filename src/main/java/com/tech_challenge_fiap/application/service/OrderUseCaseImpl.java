package com.tech_challenge_fiap.application.service;

import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;
import com.tech_challenge_fiap.core.domain.order.OrderUseCase;
import com.tech_challenge_fiap.core.domain.order.OrderRepository;
import com.tech_challenge_fiap.entrypoint.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(OrderRequestDto orderRequestDTO) {
        OrderEntity orderEntity = OrderEntity.builder().build();
        orderRepository.save(orderEntity);
    }
}
