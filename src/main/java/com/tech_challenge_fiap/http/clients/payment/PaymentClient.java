package com.tech_challenge_fiap.http.clients.payment;

import com.tech_challenge_fiap.http.clients.payment.request.PaymentRequestDto;
import com.tech_challenge_fiap.http.clients.payment.response.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final RestTemplate restTemplate;
    @Value("${payment-client-url}")
    private String paymentServiceUrl;

    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        return restTemplate.postForEntity(
                paymentServiceUrl + "/payments/", paymentRequestDto, PaymentResponseDto.class
        ).getBody();
    }
}
