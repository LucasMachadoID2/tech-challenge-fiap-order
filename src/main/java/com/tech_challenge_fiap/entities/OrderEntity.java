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
    private UUID id;
    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity client;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products;
    @OneToOne(fetch = FetchType.EAGER)
    private PaymentEntity payment;
    private LocalDateTime createdAt;
}