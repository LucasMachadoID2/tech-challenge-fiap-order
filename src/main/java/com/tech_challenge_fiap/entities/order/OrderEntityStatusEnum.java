package com.tech_challenge_fiap.entities.order;

import lombok.Getter;

@Getter
public enum OrderEntityStatusEnum {
    CREATED("Criado"),
    RECEIVED("Recebido"),
    IN_PREPARATION("Em preparação"),
    READY("Pronto"),
    FINALIZED("Finalizado"); 

    public final String description;

    OrderEntityStatusEnum(String description) {
        this.description = description;
    }
}
