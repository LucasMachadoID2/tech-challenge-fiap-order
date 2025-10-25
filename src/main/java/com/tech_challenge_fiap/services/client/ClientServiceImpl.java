package com.tech_challenge_fiap.services.client;

import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.dtos.external.ClientDTO;
import com.tech_challenge_fiap.repositories.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.tech_challenge_fiap.converter.ClientAdapter.toDomain;
import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client findById(String id) {
        ClientDTO client = clientRepository.findById(id);
        return nonNull(client) ? toDomain(client) : null;
    }
}
