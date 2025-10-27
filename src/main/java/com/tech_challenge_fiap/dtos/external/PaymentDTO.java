package com.tech_challenge_fiap.dtos.external;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaymentDTO {

    UUID id;

    String qrImage;

    String qrCode;

    String status;
}
