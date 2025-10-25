package com.tech_challenge_fiap.entities;

import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Table(name = "client")
public class ClientEntity {
    private Long id;
    private String name;
    private String cpf;
    private String email;
}