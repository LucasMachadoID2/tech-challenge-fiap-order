package com.tech_challenge_fiap.utils.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
      super(String.format("Could not found order for orderId=%s", orderId));
    }
}
