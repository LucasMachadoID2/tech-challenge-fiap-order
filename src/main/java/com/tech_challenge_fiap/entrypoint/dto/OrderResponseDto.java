package com.tech_challenge_fiap.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponseDto {

    private PaymentResponseDto paymentResponseDTO;
}
