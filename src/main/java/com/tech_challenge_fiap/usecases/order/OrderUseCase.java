package com.tech_challenge_fiap.usecases.order;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;

import java.util.List;

public interface OrderUseCase {
    OrderEntity createOrder(String clientId, List<String> productIds);

    List<OrderEntity> findAll();

    OrderEntity updateStatus(String orderId, OrderEntityStatusEnum status);
}