package com.tech_challenge_fiap.data.models;

import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@Document(collection = "order")
@Builder
public class OrderDataModel {
    @Id
    private String id;

    @NonNull
    private OrderEntityStatusEnum status;

    private ClientDataModel client;

    @NonNull
    private List<ProductDataModel> products;

    @NonNull
    private PaymentDataModel payment;

    @NonNull
    private LocalDateTime createdAt;
}
