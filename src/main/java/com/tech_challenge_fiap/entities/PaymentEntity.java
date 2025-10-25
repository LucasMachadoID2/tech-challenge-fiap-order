package com.tech_challenge_fiap.entities;

import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String qrImage;
    private String qrCode;
    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;
}