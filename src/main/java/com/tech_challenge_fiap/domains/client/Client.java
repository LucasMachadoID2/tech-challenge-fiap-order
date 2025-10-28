package com.tech_challenge_fiap.domains.client;

import com.tech_challenge_fiap.utils.exceptions.CpfWrongFormat;
import com.tech_challenge_fiap.utils.exceptions.EmailCannotBeNullOrEmptyException;
import com.tech_challenge_fiap.utils.exceptions.NameCannotBeNullOrEmptyException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static java.util.Objects.isNull;

@Getter
@Builder
public class Client {
    @Setter
    private UUID id;

    private String name;

    private String cpf;

    private String email;

    public static ClientBuilder builder() {
        return new CustomClientBuilder();
    }

    private static class CustomClientBuilder extends ClientBuilder {
        @Override
        public Client build() {
            validateName();
            validateEmail();
            validateCpf();
            return super.build();
        }

        private void validateName() {
            if (isNull(super.name) || super.name.trim().isEmpty()) {
                throw new NameCannotBeNullOrEmptyException();
            }
        }

        private void validateEmail() {
            if (isNull(super.email) || super.email.trim().isEmpty()) {
                throw new EmailCannotBeNullOrEmptyException();
            }
        }

        private void validateCpf() {
            if (isNull(super.cpf)) {
                throw new IllegalArgumentException("CPF cannot be null");
            }

            var cpfFormated = super.cpf.replaceAll("[^0-9]", "");

            if (cpfFormated.length() != 11) {
                throw new CpfWrongFormat();
            }

            super.cpf = cpfFormated;
        }
    }
}
