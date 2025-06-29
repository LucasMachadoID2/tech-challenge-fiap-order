package com.tech_challenge_fiap.utils.exceptions;

public class PriceMustBePositiveException extends RuntimeException {
    public PriceMustBePositiveException() {
        super("Price must not be null or zero or negative.");
    }
}