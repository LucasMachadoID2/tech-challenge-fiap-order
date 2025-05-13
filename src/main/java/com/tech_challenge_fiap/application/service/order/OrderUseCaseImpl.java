package com.tech_challenge_fiap.application.service.order;

import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentStatusDto;
import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.PaymentStatusEntity;
import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.order.OrderUseCase;
import com.tech_challenge_fiap.core.domain.order.OrderRepository;
import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderRequestDto;
import com.tech_challenge_fiap.core.domain.payment.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tech_challenge_fiap.util.converter.OrderConverter.orderToEntity;

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

    @Override
    public Order updatePaymentStatus(String orderId, PaymentStatusDto paymentStatusDto) {
        var order = orderRepository.getOrderById(orderId);
        order.setPaymentStatus(PaymentStatus.valueOf(paymentStatusDto.name()));

        return orderRepository.save(orderToEntity(order));
    }
}
