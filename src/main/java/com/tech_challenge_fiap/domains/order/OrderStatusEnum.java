package com.tech_challenge_fiap.domains.order;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    CREATED("Criado"),
    RECEIVED("Recebido"),
    IN_PREPARATION("Em preparação"),
    READY("Pronto"),
    FINALIZED("Finalizado"); 

    public final String description;

    OrderStatusEnum(String description) {
        this.description = description;
    }
}
