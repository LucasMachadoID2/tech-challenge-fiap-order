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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String qrImage;
    private String qrCode;
    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;
    @Column(name = "payment_id")
    private String paymentId;
    @Setter
    @OneToOne(optional = false)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            unique = true
    )
    private OrderEntity order;
}