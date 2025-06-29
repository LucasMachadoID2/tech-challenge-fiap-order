package com.tech_challenge_fiap.utils.exceptions;

public class EmailCannotBeNullOrEmptyException extends RuntimeException {
    public EmailCannotBeNullOrEmptyException() {
        super("Email cannot be null or empty");
    }
}
