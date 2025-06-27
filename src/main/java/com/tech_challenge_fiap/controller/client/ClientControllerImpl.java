package com.tech_challenge_fiap.controller.client;

import com.tech_challenge_fiap.adapters.ClientAdapter;
import com.tech_challenge_fiap.dtos.ClientRequestDto;
import com.tech_challenge_fiap.dtos.ClientResponseDto;
import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.usecases.client.ClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.tech_challenge_fiap.adapters.ClientAdapter.toEntity;
import static com.tech_challenge_fiap.adapters.ClientAdapter.toResponse;

@Component
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

    private final ClientUseCase clientUseCase;

    @Override
    public ClientResponseDto create(ClientRequestDto clientRequest) {
        ClientEntity client = clientUseCase.create(toEntity(clientRequest));
        return toResponse(client);
    }

    @Override
    public ClientResponseDto update(String id, ClientRequestDto clientRequest) {
        ClientEntity client = clientUseCase.update(id, toEntity(clientRequest));
        return toResponse(client);
    }

    @Override
    public void delete(String id) {
        clientUseCase.delete(id);
    }

    @Override
    public ClientResponseDto findById(String id) {
        ClientEntity client = clientUseCase.findById(id);
        return toResponse(client);
    }

    @Override
    public List<ClientResponseDto> findAll() {
        List<ClientEntity> clientEntities = clientUseCase.findAll();
        return clientEntities.stream().map(ClientAdapter::toResponse).collect(Collectors.toList());
    }
}
