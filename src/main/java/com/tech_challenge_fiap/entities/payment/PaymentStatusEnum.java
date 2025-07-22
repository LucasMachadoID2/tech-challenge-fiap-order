package com.tech_challenge_fiap.entities.payment;

import com.tech_challenge_fiap.utils.exceptions.NotSupportedPaymentStatusException;
import lombok.Getter;

@Getter
public enum PaymentStatusEnum {
    CREATED("Criado"),
    PAID("Pago"),
    REFUSED("Recusado");

    public final String description;

    public static PaymentStatusEnum safeValueOf(String status) {
        try {
            return PaymentStatusEnum.valueOf(status);
        } catch(Exception e) {
            throw new NotSupportedPaymentStatusException(status);
        }
    }

    PaymentStatusEnum(String description) {
        this.description = description;
    }
}
