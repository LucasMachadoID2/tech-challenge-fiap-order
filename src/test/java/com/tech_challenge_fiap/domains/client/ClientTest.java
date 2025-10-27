package com.tech_challenge_fiap.domains.client;

import com.tech_challenge_fiap.utils.exceptions.CpfWrongFormat;
import com.tech_challenge_fiap.utils.exceptions.EmailCannotBeNullOrEmptyException;
import com.tech_challenge_fiap.utils.exceptions.NameCannotBeNullOrEmptyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class ClientTest {

    @Test
    void shouldBuildClassWithSuccess() {
        assertDoesNotThrow(() -> Client.builder()
                .id(1L)
                .name("Test")
                .cpf("111.111.111-11")
                .email("mail@mail.com.br")
                .build());
    }

    @Test
    void shouldThrowsNameCannotBeNullOrEmptyExceptionWhenNameIsNull() {
        assertThrows(NameCannotBeNullOrEmptyException.class, () -> {
            Client.builder()
                    .id(1L)
                    .name(null)
                    .cpf("11111111111")
                    .email("mail@mail.com.br")
                    .build();
        });
    }

    @Test
    void shouldThrowsNameCannotBeNullOrEmptyExceptionWhenNameIsEmpty() {
        assertThrows(NameCannotBeNullOrEmptyException.class, () -> {
            Client.builder()
                    .id(1L)
                    .name(EMPTY)
                    .cpf("11111111111")
                    .email("mail@mail.com.br")
                    .build();
        });
    }

    @Test
    void shouldThrowsEmailCannotBeNullOrEmptyExceptionWhenEmailIsNull() {
        assertThrows(EmailCannotBeNullOrEmptyException.class, () -> {
            Client.builder()
                    .id(1L)
                    .name("Test")
                    .cpf("11111111111")
                    .email(null)
                    .build();
        });
    }

    @Test
    void shouldThrowsEmailCannotBeNullOrEmptyExceptionWhenEmailIsEmpty() {
        assertThrows(EmailCannotBeNullOrEmptyException.class, () -> {
            Client.builder()
                    .id(1L)
                    .name("Test")
                    .cpf("11111111111")
                    .email(EMPTY)
                    .build();
        });
    }

    @Test
    void shouldThrowsIllegalArgumentExceptionWhenCpfIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Client.builder()
                    .id(1L)
                    .name("Test")
                    .cpf(null)
                    .email("mail@mail.com.br")
                    .build();
        });
    }

    @Test
    void shouldThrowsCpfWrongFormatWhenCpfDoesntContains11Numbers() {
        assertThrows(CpfWrongFormat.class, () -> {
            Client.builder()
                    .id(1L)
                    .name("Test")
                    .cpf("1231231231231")
                    .email("mail@mail.com.br")
                    .build();
        });
    }
}
