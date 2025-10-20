package com.tech_challenge_fiap.data.models;

import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Table(name = "order")
public class OrderDataModel {

    private String id;
    private OrderEntityStatusEnum status;
    private ClientDataModel client;
    private List<ProductDataModel> products;
    private PaymentDataModel payment;
    private LocalDateTime createdAt;
}