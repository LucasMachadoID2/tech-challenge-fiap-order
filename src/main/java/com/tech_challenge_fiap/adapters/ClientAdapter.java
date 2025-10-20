package com.tech_challenge_fiap.adapters;

import com.tech_challenge_fiap.data.models.ClientDataModel;
import com.tech_challenge_fiap.dtos.external.ClientDTO;
import com.tech_challenge_fiap.dtos.internal.ClientRequestDto;
import com.tech_challenge_fiap.dtos.internal.ClientResponseDto;
import com.tech_challenge_fiap.entities.client.ClientEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientAdapter {

    public static ClientEntity toEntity(ClientDataModel entity) {
        return ClientEntity.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .email(entity.getEmail())
                .build();
    }

    public static ClientDataModel toDataModel(ClientEntity clientEntity) {
        return ClientDataModel.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .cpf(clientEntity.getCpf()) 
                .email(clientEntity.getEmail())
                .build();
    }

    public static ClientEntity toEntity(ClientRequestDto dto) {
        return ClientEntity.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .build();
    }

    public static ClientResponseDto toResponse(ClientEntity clientEntity) {
        return ClientResponseDto.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .cpf(clientEntity.getCpf())
                .email(clientEntity.getEmail())
                .build();
    }

    public static ClientEntity toEntity(ClientDTO dto) {
        return ClientEntity.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .build();
    }
}