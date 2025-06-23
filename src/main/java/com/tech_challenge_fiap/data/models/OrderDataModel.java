package com.tech_challenge_fiap.data.models;

import com.tech_challenge_fiap.adapter.service.outbound.entity.ClientEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.PaymentEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.ProductEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private ClientEntity client;

    @NonNull
    private List<ProductEntity> products;

    @NonNull
    private PaymentEntity payment;
}
