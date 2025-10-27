package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ClientConverterTest {

    @Test
    void shouldConvertToDomainFromEntity() {
        var entity = ClientEntity.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .cpf("123.456.789-00")
                .email("mail@mail.com.br")
                .build();

        var client = ClientConverter.toDomain(entity);

        assertEquals(entity.getId(), client.getId());
        assertEquals(entity.getName(), client.getName());
        assertEquals(entity.getCpf().replaceAll("[^0-9]", ""), client.getCpf());
        assertEquals(entity.getEmail(), client.getEmail());
    }

    @Test
    void shouldConvertToEntityFromDomain() {
        var client = Client.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .cpf("123.456.789-00")
                .email("mail@mail.com.br")
                .build();

        var entity = ClientConverter.toEntity(client);

        assertEquals(client.getId(), entity.getId());
        assertEquals(client.getName(), entity.getName());
        assertEquals(client.getCpf(), entity.getCpf());
        assertEquals(client.getEmail(), entity.getEmail());
    }

    @Test
    void shouldConvertToResponseFromDomain() {
        var client = Client.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .cpf("123.456.789-00")
                .email("mail@mail.com.br")
                .build();

        var response = ClientConverter.toResponse(client);

        assertEquals(client.getId(), response.getId());
        assertEquals(client.getName(), response.getName());
        assertEquals(client.getCpf(), response.getCpf());
        assertEquals(client.getEmail(), response.getEmail());
    }

    @Test
    void shouldConvertToResponseFromEntity() {
        var entity = ClientEntity.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .cpf("123.456.789-00")
                .email("mail@mail.com.br")
                .build();

        var response = ClientConverter.toResponse(entity);

        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getName(), response.getName());
        assertEquals(entity.getCpf(), response.getCpf());
        assertEquals(entity.getEmail(), response.getEmail());
    }
}
