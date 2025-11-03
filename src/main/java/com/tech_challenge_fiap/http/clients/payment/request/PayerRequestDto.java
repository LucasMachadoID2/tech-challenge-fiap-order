package com.tech_challenge_fiap.http.clients.payment.request;

import lombok.Builder;

@Builder
public class PayerRequestDto {
    private String email;
    private String firstName;
    private String lastName;
}
