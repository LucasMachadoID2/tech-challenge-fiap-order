package com.tech_challenge_fiap.http.clients.product;

import com.tech_challenge_fiap.domains.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestTemplate restTemplate;

    public Product getProductbyId(UUID productId) {
        // TODO: request para o microserviço de produtos
        return null;
    }
}
