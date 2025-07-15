package com.tech_challenge_fiap.entities.order;

public enum OrderEntityStatusEnum {
    CREATED("Criado"),
    RECEIVED("Recebido"),
    IN_PREPARATION("Em preparação"),
    FINALIZED("Finalizado"); 

    public final String description;

    OrderEntityStatusEnum(String description) {
        this.description = description;
    }
}
