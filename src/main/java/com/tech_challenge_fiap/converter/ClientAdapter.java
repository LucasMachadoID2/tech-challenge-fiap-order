package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.dtos.external.ClientDTO;
import com.tech_challenge_fiap.dtos.internal.ClientRequestDto;
import com.tech_challenge_fiap.dtos.internal.ClientResponseDto;
import com.tech_challenge_fiap.entities.ClientEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientAdapter {

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
        return Client.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
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


    public static ClientResponseDto toResponse(ClientEntity client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .email(client.getEmail())
                .build();
    }

    public static Client toDomain(ClientDTO dto) {
        return Client.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .build();
    }
}