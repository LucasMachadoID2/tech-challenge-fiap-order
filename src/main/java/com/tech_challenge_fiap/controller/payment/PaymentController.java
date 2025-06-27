package com.tech_challenge_fiap.controller.payment;

import com.tech_challenge_fiap.dtos.PaymentRequestDto;

public interface PaymentController {
    void updateStatus(PaymentRequestDto paymentRequestDto);
}
