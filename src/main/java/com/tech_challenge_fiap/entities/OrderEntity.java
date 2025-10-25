package com.tech_challenge_fiap.entities;

import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Table(name = "order")
public class OrderEntity {

    private Long id;
    @Setter
    private OrderStatusEnum status;
    private ClientEntity client;
    private List<ProductEntity> products;
    private PaymentEntity payment;
    private LocalDateTime createdAt;
}