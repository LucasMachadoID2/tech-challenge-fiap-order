package com.tech_challenge_fiap.data.models;

import com.tech_challenge_fiap.utils.enums.PaymentStatusEnumEntity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Builder
@Table(name = "payment")
public class PaymentDataModel {

    private String id;
    private String qrImage;
    private String qrCode;
    private PaymentStatusEnumEntity status;
}