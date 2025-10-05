package com.tech_challenge_fiap.controller.client;

import com.tech_challenge_fiap.dtos.ClientRequestDto;
import com.tech_challenge_fiap.dtos.ClientResponseDto;

import java.util.List;

public interface ClientController {
    ClientResponseDto create(ClientRequestDto clientEntity);

    ClientResponseDto update(String id, ClientRequestDto clientEntity);

    void delete(String id);

    ClientResponseDto findById(String id);

    List<ClientResponseDto> findAll();

    ClientResponseDto findByCpf(String cpf);
}
