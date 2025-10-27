package com.tech_challenge_fiap.http.clients.client;

import com.tech_challenge_fiap.domains.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientClient {

    private final RestTemplate restTemplate;

    public Client getClientById(UUID clientId) {
        // TODO: request para o microserviço de clientes
        return null;
    }
}
