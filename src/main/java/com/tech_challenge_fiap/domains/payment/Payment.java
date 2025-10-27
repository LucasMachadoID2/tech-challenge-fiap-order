package com.tech_challenge_fiap.domains.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Payment {
    private UUID id;

    String qrImage;

    String qrCode;

    PaymentStatusEnum status;
}
