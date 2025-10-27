package com.tech_challenge_fiap.utils.exceptions;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Long paymentId) {
        super(String.format("Could not found payment for paymentId=%s", paymentId));
    }
}