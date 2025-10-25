package com.tech_challenge_fiap.utils.exceptions;

public class CouldNotCreatePaymentException extends RuntimeException {
    public CouldNotCreatePaymentException(Long orderId, Throwable cause) {
        super(String.format("Could not create payment for orderId=%s", orderId), cause);
    }
}
