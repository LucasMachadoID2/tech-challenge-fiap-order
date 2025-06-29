package com.tech_challenge_fiap.utils.exceptions;

public class CpfWrongFormat extends RuntimeException {
    public CpfWrongFormat() {
        super("CPF should have 11 digits");
    }
}
