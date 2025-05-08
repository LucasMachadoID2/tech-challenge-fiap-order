package com.tech_challenge_fiap.adapter.service.inbound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PaymentResponseDto {

    private String qrCodeImage;
    private String qrCode;
}
