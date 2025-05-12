package com.tech_challenge_fiap.core.domain.client;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client {
    private String id;
    private String name;
    private String cpf;
    private String email;
}
