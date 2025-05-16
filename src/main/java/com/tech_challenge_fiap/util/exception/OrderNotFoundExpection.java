package com.tech_challenge_fiap.util.exception;

public class OrderNotFoundExpection extends RuntimeException {
    public OrderNotFoundExpection(String orderId) {
      super(String.format("Could not fount order for orderId=%s", orderId));
    }
}
