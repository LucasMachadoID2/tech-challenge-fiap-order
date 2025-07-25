package com.tech_challenge_fiap.usecases;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.gateways.order.OrderGateway;

import java.util.List;

public class OrderUseCase {

    public static List<OrderEntity> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated(OrderGateway orderGateway) {
        return orderGateway.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
    }

    public static OrderEntity updateStatus(String orderId, OrderEntityStatusEnum status, OrderGateway orderGateway) {
        OrderEntity orderEntityFound = orderGateway.getOrderById(orderId);

        orderEntityFound.setStatus(status);

        return orderGateway.save(orderEntityFound);
    }
}
