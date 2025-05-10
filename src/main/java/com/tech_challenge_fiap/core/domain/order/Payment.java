package com.tech_challenge_fiap.core.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Payment {
    String id;
    String qrImage;
    String qrCode;
    PaymentStatus status;
}
