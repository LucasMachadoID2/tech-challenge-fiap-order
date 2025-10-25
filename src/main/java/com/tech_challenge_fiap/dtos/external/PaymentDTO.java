package com.tech_challenge_fiap.dtos.external;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PaymentDTO {

    Long id;

    String qrImage;

    String qrCode;

    String status;
}
