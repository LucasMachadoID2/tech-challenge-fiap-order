package com.tech_challenge_fiap.utils.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long clientId) {
        super(String.format("Could not found client for clientId=%s", clientId));
    }
}