package com.tech_challenge_fiap.entities.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentEntity {
    String id;

    String qrImage;

    String qrCode;

    PaymentStatusEnum status;
}
