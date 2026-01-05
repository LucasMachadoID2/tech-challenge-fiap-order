package com.tech_challenge_fiap.utils.exceptions;

import java.util.UUID;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String paymentId) {
        super(String.format("Could not found payment for paymentId=%s", paymentId));
    }
}