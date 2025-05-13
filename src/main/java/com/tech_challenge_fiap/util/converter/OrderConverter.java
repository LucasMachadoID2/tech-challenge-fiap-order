package com.tech_challenge_fiap.util.converter;

import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.PaymentStatusEntity;
import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.payment.PaymentStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static Order entityToOrder(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .paymentStatus(PaymentStatus.valueOf(orderEntity.getPaymentStatus().name()))
                .build();
    }

    public static OrderEntity orderToEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .paymentStatus(PaymentStatusEntity.valueOf(order.getPaymentStatus().name()))
                .build();
    }
}
