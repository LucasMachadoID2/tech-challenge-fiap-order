package com.tech_challenge_fiap.controller.payment;

import com.tech_challenge_fiap.dtos.PaymentRequestDto;
import com.tech_challenge_fiap.usecases.payment.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController {

    private final PaymentUseCase paymentUseCase;

    @Override
    public void updateStatus(PaymentRequestDto paymentRequestDto) {
        paymentUseCase.updateStatus(paymentRequestDto.getOrderId(), paymentRequestDto.getStatus());
    }
}
