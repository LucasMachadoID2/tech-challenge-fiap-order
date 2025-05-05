package com.tech_challenge_fiap.util.converter;

import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;
import com.tech_challenge_fiap.core.domain.order.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static Order toOrderEntity(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .build();
    }
}
