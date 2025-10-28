package com.tech_challenge_fiap.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    private UUID id;
    private String name;
    private String cpf;
    private String email;
}