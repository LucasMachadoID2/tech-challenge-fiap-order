package com.tech_challenge_fiap.utils.exceptions;

public class OrderProductsCantBeNullOrEmpty extends RuntimeException {
    public OrderProductsCantBeNullOrEmpty() {
        super("Products cannot be null or empty");
    }
}
