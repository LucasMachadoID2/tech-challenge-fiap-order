package com.tech_challenge_fiap.http.clients.payment;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.dtos.external.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final RestTemplate restTemplate;

    public PaymentDTO createPayment(Order order) {
        // TODO: request para o microserviço de pagamentos
        return new PaymentDTO();
    }
}
