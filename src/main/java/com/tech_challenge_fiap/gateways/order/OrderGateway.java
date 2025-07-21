package com.tech_challenge_fiap.gateways.order;

import com.tech_challenge_fiap.entities.order.OrderEntity;

import java.util.List;

public interface OrderGateway {
    OrderEntity save(OrderEntity order);

    OrderEntity getOrderById(String id);

    List<OrderEntity> findAllOrderedByStatusAndCreatedAtIgnoringFinalized();
}
