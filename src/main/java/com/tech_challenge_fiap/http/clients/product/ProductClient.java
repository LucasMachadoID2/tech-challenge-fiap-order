package com.tech_challenge_fiap.http.clients.product;

import com.tech_challenge_fiap.http.clients.product.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestTemplate restTemplate;
    @Value("${product-client-url}")
    private String productServiceUrl;

    public ProductResponseDto getProductbyId(String productId) {
        return restTemplate.getForEntity(
                productServiceUrl + "v1/products/" + productId, ProductResponseDto.class
        ).getBody();
    }
}
