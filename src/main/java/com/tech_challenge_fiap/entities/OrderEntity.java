package com.tech_challenge_fiap.entities;

import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ClientEntity client;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductEntity> products;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PaymentEntity payment;
    private LocalDateTime createdAt;
}