package com.tech_challenge_fiap.http.clients.payment.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PayerRequestDto {
    private String email;
    private String firstName;
    private String lastName;
}
