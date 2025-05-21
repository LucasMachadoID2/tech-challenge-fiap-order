package com.tech_challenge_fiap.adapter.service.inbound.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class ClientRequestDto {
    private String name;
    @NotBlank(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF deve ter exatamente 11 dígitos")
    private String cpf;
    private String email;
}
