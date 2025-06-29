package com.tech_challenge_fiap.usecases.payment;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.usecases.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

    private final OrderUseCase orderUseCase;

    @Override
    public OrderEntity updateStatus(String orderId, String status) {
        return orderUseCase.updatePaymentStatus(orderId, status);
    }
}
