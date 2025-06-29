package com.tech_challenge_fiap.data.models;

import com.tech_challenge_fiap.utils.enums.PaymentStatusEnumEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@Getter
@Setter
@Document(collection = "payment")
@Builder
public class PaymentDataModel {
    @Id
    String id;

    String qrImage;

    String qrCode;

    PaymentStatusEnumEntity status;
}
