package com.tech_challenge_fiap.services.client;

import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.entities.ClientEntity;
import com.tech_challenge_fiap.repositories.client.ClientRepository;
import com.tech_challenge_fiap.utils.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.tech_challenge_fiap.converter.ClientAdapter.toDomain;
import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client findById(Long id) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        return nonNull(client) ? toDomain(client) : null;
    }
}
