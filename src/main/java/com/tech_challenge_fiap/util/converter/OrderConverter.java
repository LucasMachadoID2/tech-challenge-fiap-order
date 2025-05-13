package com.tech_challenge_fiap.util.converter;

import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.PaymentEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.PaymentStatusEnumEntity;
import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.payment.Payment;
import com.tech_challenge_fiap.core.domain.payment.PaymentStatusEnum;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static Order entityToOrder(OrderEntity orderEntity) {
        var payment = Payment.builder()
                .id(orderEntity.getPayment().getId())
                .qrCode(orderEntity.getPayment().getQrCode())
                .qrImage(orderEntity.getPayment().getQrImage())
                .status(PaymentStatusEnum.valueOf(orderEntity.getPayment().getStatus().name()))
                .build();

        return Order.builder()
                .id(orderEntity.getId())
                .payment(payment)
                .build();
    }

    public static OrderEntity orderToEntity(Order order) {
        var paymentEntity = PaymentEntity.builder()
                .id(order.getPayment().getId())
                .qrCode(order.getPayment().getQrCode())
                .qrImage(order.getPayment().getQrImage())
                .status(PaymentStatusEnumEntity.valueOf(order.getPayment().getStatus().name()))
                .build();

        return OrderEntity.builder()
                .id(order.getId())
                .payment(paymentEntity)
                .build();
    }
}
