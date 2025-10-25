package com.tech_challenge_fiap.dtos.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PaymentResponseDto {
    private Long id;

    private String qrImage;

    private String qrCode;

    private String status;
}
