package com.tech_challenge_fiap.adapter.service.outbound.entity;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String email;
}
