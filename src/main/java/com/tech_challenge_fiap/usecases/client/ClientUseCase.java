package com.tech_challenge_fiap.usecases.client;

import com.tech_challenge_fiap.entities.client.ClientEntity;

import java.util.List;

public interface ClientUseCase {
    ClientEntity create(ClientEntity clientEntity);

    ClientEntity update(String id, ClientEntity clientEntity);

    void delete(String id);

    ClientEntity findById(String id);

    List<ClientEntity> findAll();
}
