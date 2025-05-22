package com.tech_challenge_fiap.adapter.service.outbound.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@Getter
@Setter
@Document
@Builder
public class PaymentEntity {
    @Id
    String id;
    String qrImage;
    String qrCode;
    PaymentStatusEnumEntity status;
}
