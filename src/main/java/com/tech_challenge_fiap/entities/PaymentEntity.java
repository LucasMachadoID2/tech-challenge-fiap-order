package com.tech_challenge_fiap.entities;

import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    private UUID id;
    private String qrImage;
    private String qrCode;
    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;
}