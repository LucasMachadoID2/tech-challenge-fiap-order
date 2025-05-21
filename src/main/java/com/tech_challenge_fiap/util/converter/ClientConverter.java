package com.tech_challenge_fiap.util.converter;

import com.tech_challenge_fiap.adapter.service.inbound.dto.ClientRequestDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.ClientResponseDto;
import com.tech_challenge_fiap.core.domain.client.Client;
import com.tech_challenge_fiap.adapter.service.outbound.entity.ClientEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientConverter {

    public static Client toDomain(ClientEntity entity) {
        return Client.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .email(entity.getEmail())
                .build();
    }

    public static ClientEntity toEntity(Client client) {
        return ClientEntity.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .email(client.getEmail())
                .build();
    }

    public static Client toDomain(ClientRequestDto dto) {
        String cpf = dto.getCpf();
        if (cpf != null) {
            cpf = cpf.replaceAll("[^0-9]", "");
        }
        return Client.builder()
                .name(dto.getName())
                .cpf(cpf)
                .email(dto.getEmail())
                .build();
    }

    public static ClientResponseDto toResponse(Client client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .email(client.getEmail())
                .build();
    }
}

