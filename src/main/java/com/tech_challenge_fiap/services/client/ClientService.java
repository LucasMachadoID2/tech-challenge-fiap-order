package com.tech_challenge_fiap.services.client;

import com.tech_challenge_fiap.domains.client.Client;

public interface ClientService {

    Client findById(String id);
}
