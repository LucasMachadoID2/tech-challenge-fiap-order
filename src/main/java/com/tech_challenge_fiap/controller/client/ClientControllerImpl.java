package com.tech_challenge_fiap.controller.client;

import com.tech_challenge_fiap.adapters.ClientAdapter;
import com.tech_challenge_fiap.dtos.ClientRequestDto;
import com.tech_challenge_fiap.dtos.ClientResponseDto;
import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.usecases.ClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static com.tech_challenge_fiap.adapters.ClientAdapter.toEntity;
import static com.tech_challenge_fiap.adapters.ClientAdapter.toResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientControllerImpl implements ClientController {

    private final ClientGateway clientGateway;

    @Override
    public ClientResponseDto create(ClientRequestDto clientRequest) {
        log.info("[Controller] Recebido DTO: name={}, cpf={}, email={}", 
             clientRequest.getName(), clientRequest.getCpf(), clientRequest.getEmail());

        ClientEntity client = ClientUseCase.create(toEntity(clientRequest), clientGateway);
        return toResponse(client);
    }

    @Override
    public ClientResponseDto update(String id, ClientRequestDto clientRequest) {
        ClientEntity client = ClientUseCase.update(id, toEntity(clientRequest), clientGateway);
        return toResponse(client);
    }

    @Override
    public void delete(String id) {
        ClientUseCase.delete(id, clientGateway);
    }

    @Override
    public ClientResponseDto findById(String id) {
        ClientEntity client = ClientUseCase.findById(id, clientGateway);
        return toResponse(client);
    }

    @Override
    public List<ClientResponseDto> findAll() {
        List<ClientEntity> clientEntities = ClientUseCase.findAll(clientGateway);
        return clientEntities.stream().map(ClientAdapter::toResponse).collect(Collectors.toList());
    }

    @Override
    public ClientResponseDto findByCpf(String cpf) {
        ClientEntity client = ClientUseCase.findByCpf(cpf, clientGateway);
        return toResponse(client);
    }
}
