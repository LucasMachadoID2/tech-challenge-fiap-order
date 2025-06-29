package com.tech_challenge_fiap.utils.exceptions;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(String cpf) {
        super("Já existe um cliente cadastrado com o CPF: " + cpf);
    }
}
