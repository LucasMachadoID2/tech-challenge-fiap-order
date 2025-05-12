package com.tech_challenge_fiap.application.service;

import com.tech_challenge_fiap.adapter.service.outbound.entity.ClientEntity;
import com.tech_challenge_fiap.adapter.service.outbound.repository.MongoClientRepository;
import com.tech_challenge_fiap.core.domain.client.Client;
import com.tech_challenge_fiap.core.domain.client.ClientUseCase;
import com.tech_challenge_fiap.util.converter.ClientConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tech_challenge_fiap.util.converter.ClientConverter.*;

@Service
@RequiredArgsConstructor
public class ClientUseCaseImpl implements ClientUseCase {

    private final MongoClientRepository mongoRepository;

    @Override
    public Client create(Client client) {
        return toDomain(mongoRepository.save(toEntity(client)));
    }

    @Override
    public Client update(String id, Client client) {
        ClientEntity entity = toEntity(client);
        entity.setId(id);
        return toDomain(mongoRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public Client findById(String id) {
        return mongoRepository.findById(id)
                .map(ClientConverter::toDomain)
                .orElse(null);
    }

    @Override
    public List<Client> findAll() {
        return mongoRepository.findAll().stream()
                .map(ClientConverter::toDomain)
                .toList();
    }
}
