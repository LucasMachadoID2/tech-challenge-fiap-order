package com.tech_challenge_fiap.http.clients.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClientResponseDto {
    private String id;
    private String name;
    private String cpf;
    private String email;
}
