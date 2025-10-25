package com.tech_challenge_fiap.repositories.client;

import com.tech_challenge_fiap.domains.client.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Override
    public Client getClientById(Long clientId) {
        // TODO: request para o microserviço de clientes
        return null;
    }
}
