package com.tech_challenge_fiap.adapter.service.inbound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponseDto {

    private PaymentResponseDto paymentResponseDTO;
}
