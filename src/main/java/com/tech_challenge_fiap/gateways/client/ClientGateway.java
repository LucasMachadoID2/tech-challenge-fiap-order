package com.tech_challenge_fiap.gateways.client;

import com.tech_challenge_fiap.entities.client.ClientEntity;

public interface ClientGateway {
    ClientEntity findById(String id);
}
