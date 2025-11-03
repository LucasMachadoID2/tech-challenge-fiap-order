package com.tech_challenge_fiap.http.clients.payment.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PaymentResponseDto {
    String id;

    String qrImage;

    String qrCode;

    String status;
}
