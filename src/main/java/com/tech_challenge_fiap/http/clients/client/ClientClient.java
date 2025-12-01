package com.tech_challenge_fiap.http.clients.client;

import com.tech_challenge_fiap.http.clients.client.response.ClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientClient {

    private final RestTemplate restTemplate;
    @Value("${client-client-url}")
    private String clientServiceUrl;

    public ClientResponseDto getClientById(String clientId) {
        try {
            return restTemplate.getForEntity(
                    clientServiceUrl + "/v1/clients/" + clientId, ClientResponseDto.class
            ).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }
}
