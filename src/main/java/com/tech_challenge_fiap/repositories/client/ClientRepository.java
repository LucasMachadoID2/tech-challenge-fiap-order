package com.tech_challenge_fiap.repositories.client;

import com.tech_challenge_fiap.domains.client.Client;

public interface ClientRepository {

    Client getClientById(Long clientId);
}
