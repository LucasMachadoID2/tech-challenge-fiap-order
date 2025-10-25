package com.tech_challenge_fiap.domains.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Payment {
    Long id;

    String qrImage;

    String qrCode;

    PaymentStatusEnum status;
}
