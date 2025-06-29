package com.tech_challenge_fiap.utils.exceptions;

public class OrdersNotFoundExpection extends RuntimeException {
    public OrdersNotFoundExpection() {
      super("Could not found any order");
    }
}
