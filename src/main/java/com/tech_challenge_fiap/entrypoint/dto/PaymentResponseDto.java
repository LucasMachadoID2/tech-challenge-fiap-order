package com.tech_challenge_fiap.entrypoint.dto;

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
