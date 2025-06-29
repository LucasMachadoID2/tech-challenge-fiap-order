package com.tech_challenge_fiap.entities.order;

public enum OrderEntityStatusEnum {
    CREATED("Criado"),
    RECEIVED("Recebido"),
    IN_PREPARATION("Em preparação");

    public final String description;

    OrderEntityStatusEnum(String description) {
        this.description = description;
    }
}
