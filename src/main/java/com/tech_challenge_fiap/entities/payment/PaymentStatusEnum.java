package com.tech_challenge_fiap.entities.payment;

import lombok.Getter;

@Getter
public enum PaymentStatusEnum {
    CREATED("Criado"),
    PAID("Pago"),
    REFUSED("Recusado");

    public final String description;

    PaymentStatusEnum(String description) {
        this.description = description;
    }
}
