package com.tech_challenge_fiap.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    @Column(name = "client_id")
    private String clientId;
    @Setter
    @OneToOne(optional = false)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            unique = true
    )
    private OrderEntity order;
}