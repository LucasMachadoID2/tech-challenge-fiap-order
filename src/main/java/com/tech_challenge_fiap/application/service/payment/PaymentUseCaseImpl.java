package com.tech_challenge_fiap.application.service.payment;

import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.order.OrderRepository;
import com.tech_challenge_fiap.core.domain.payment.PaymentStatus;
import com.tech_challenge_fiap.core.domain.payment.PaymentUseCase;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tech_challenge_fiap.util.converter.OrderConverter.orderToEntity;

@Service
@RequiredArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

    private final OrderRepository orderRepository;

    @Override
    public Order updatePaymentStatus(PaymentRequestDto paymentRequestDto) {
        var order = orderRepository.getOrderById(paymentRequestDto.getOrderId());
        order.setPaymentStatus(PaymentStatus.valueOf(paymentRequestDto.getStatus().name()));

        var entityOrder = orderToEntity(order);

        return orderRepository.save(entityOrder);
    }
}
