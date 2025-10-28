package com.tech_challenge_fiap.utils.exceptions;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(UUID orderId) {
      super(String.format("Could not found order for orderId=%s", orderId));
    }
}
