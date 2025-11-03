package com.tech_challenge_fiap.http.clients.payment.request;

import lombok.Builder;

@Builder
public class PaymentRequestDto {
    private Long amount;
    private String paymentMethod;
    private PayerRequestDto payer;
}
