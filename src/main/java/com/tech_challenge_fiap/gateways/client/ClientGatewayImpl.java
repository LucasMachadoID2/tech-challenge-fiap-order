package com.tech_challenge_fiap.gateways.client;

import com.tech_challenge_fiap.dtos.external.ClientDTO;
import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.repositories.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.tech_challenge_fiap.adapters.ClientAdapter.toEntity;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class ClientGatewayImpl implements ClientGateway {

    private final ClientRepository clientRepository;

   @Override
    public ClientEntity findById(String id) {
        ClientDTO client = clientRepository.findById(id);
        return nonNull(client) ? toEntity(client) : null;
    }
}
