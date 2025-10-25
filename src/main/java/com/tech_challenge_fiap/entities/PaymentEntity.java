package com.tech_challenge_fiap.entities;

import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Table(name = "payment")
public class PaymentEntity {

    private Long id;
    private String qrImage;
    private String qrCode;
    @Setter
    private PaymentStatusEnum status;
}