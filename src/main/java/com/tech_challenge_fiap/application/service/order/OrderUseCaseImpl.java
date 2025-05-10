package com.tech_challenge_fiap.application.service.order;

import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.PaymentStatusEntity;
import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.order.OrderUseCase;
import com.tech_challenge_fiap.core.domain.order.OrderRepository;
import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderRequestDto orderRequestDTO) {
        OrderEntity orderEntity = OrderEntity.builder()
                .paymentStatus(PaymentStatusEntity.WAITING_PAYMENT)
                .build();
        return orderRepository.save(orderEntity);
    }
}
