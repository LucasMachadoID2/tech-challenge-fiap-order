package com.tech_challenge_fiap.utils.exceptions;

import java.util.UUID;

public class CouldNotCreatePaymentException extends RuntimeException {
    public CouldNotCreatePaymentException(UUID orderId, Throwable cause) {
        super(String.format("Could not create payment for orderId=%s", orderId), cause);
    }
}
