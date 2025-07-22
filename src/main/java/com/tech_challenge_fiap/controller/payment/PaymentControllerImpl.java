package com.tech_challenge_fiap.controller.payment;

import com.tech_challenge_fiap.dtos.PaymentRequestDto;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import com.tech_challenge_fiap.usecases.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController {

    private final OrderGateway orderGateway;

    @Override
    public void updateStatus(PaymentRequestDto paymentRequestDto) {
        PaymentUseCase.updateStatus(paymentRequestDto.getOrderId(), paymentRequestDto.getStatus(), orderGateway);
    }
}
