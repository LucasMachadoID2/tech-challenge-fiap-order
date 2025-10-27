package com.tech_challenge_fiap.dtos.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaymentDTO {

    Long id;

    String qrImage;

    String qrCode;

    String status;
}
