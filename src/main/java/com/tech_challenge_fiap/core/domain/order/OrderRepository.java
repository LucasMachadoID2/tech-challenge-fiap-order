package com.tech_challenge_fiap.core.domain.order;

import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;

public interface OrderRepository {

    Order save(OrderEntity order);
    Order getOrderById(String id);
}
