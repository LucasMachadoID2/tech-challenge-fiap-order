package com.tech_challenge_fiap.usecases;

import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.gateways.order.OrderGateway;

public class PaymentUseCase {

    public static OrderEntity updateStatus(String orderId, String status, OrderGateway orderGateway) {
        var order = orderGateway.getOrderById(orderId);

        PaymentStatusEnum paymentStatusEnum = PaymentStatusEnum.safeValueOf(status);
        order.getPaymentEntity().setStatus(paymentStatusEnum);

        return orderGateway.save(order);
    }
}
