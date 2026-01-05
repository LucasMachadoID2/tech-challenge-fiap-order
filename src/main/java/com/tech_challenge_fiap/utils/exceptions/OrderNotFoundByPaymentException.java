package com.tech_challenge_fiap.utils.exceptions;

import java.util.UUID;

public class OrderNotFoundByPaymentException extends RuntimeException {
    public OrderNotFoundByPaymentException(String paymentId) {
        super(String.format("Could not found order for paymentId=%s", paymentId));
    }
}
