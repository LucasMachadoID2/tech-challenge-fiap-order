package com.tech_challenge_fiap.adapter.service.inbound.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import jakarta.validation.constraints.NotBlank;
import com.tech_challenge_fiap.util.validation.Cpf;

@Builder
@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class ClientRequestDto {
    private String name;
    @NotBlank(message = "CPF é obrigatório")
    @Cpf
    private String cpf;
    private String email;
}
