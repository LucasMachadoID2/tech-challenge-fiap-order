package com.tech_challenge_fiap.application.service.payment;

import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentRequestDto;
import com.tech_challenge_fiap.core.domain.payment.PaymentUseCase;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.usecases.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

    private final OrderUseCase orderUseCase;

    @Override
    public OrderEntity updatePaymentStatus(PaymentRequestDto paymentRequestDto) {
       return orderUseCase.updatePaymentStatus(paymentRequestDto);
    }
}
