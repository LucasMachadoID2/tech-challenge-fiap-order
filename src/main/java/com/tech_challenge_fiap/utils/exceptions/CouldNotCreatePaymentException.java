package com.tech_challenge_fiap.utils.exceptions;

import java.util.UUID;

public class CouldNotCreatePaymentException extends RuntimeException {
    public CouldNotCreatePaymentException(UUID orderId, String exceptionMessage) {
        super(String.format("Could not create payment for orderId=%s, exception=%s", orderId, exceptionMessage));
    }
}
