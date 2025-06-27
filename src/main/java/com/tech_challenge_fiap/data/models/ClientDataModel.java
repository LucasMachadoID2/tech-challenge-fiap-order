package com.tech_challenge_fiap.data.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
@Getter
@Setter
@Builder
public class ClientDataModel {
    @Id
    private String id;

    private String name;

    private String cpf;

    private String email;
}
