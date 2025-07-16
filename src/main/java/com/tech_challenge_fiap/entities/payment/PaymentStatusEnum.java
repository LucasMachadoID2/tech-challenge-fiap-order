package com.tech_challenge_fiap.entities.payment;

import com.tech_challenge_fiap.utils.exceptions.NotSupportedPaymentStatusException;

public enum PaymentStatusEnum {
    CRIADO,
    PAGO,
    RECUSADO;

    public static PaymentStatusEnum safeValueOf(String status) {
        try {
            return PaymentStatusEnum.valueOf(status);
        } catch(Exception e) {
            throw new NotSupportedPaymentStatusException(status);
        }
    }
}
