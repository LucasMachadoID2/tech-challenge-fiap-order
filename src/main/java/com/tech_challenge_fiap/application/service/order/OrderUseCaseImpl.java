package com.tech_challenge_fiap.application.service.order;

import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentStatusDto;
import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.PaymentEntity;
import com.tech_challenge_fiap.adapter.service.outbound.entity.PaymentStatusEnumEntity;
import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.order.OrderUseCase;
import com.tech_challenge_fiap.core.domain.order.OrderRepository;
import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderRequestDto;
import com.tech_challenge_fiap.core.domain.payment.Payment;
import com.tech_challenge_fiap.core.domain.payment.PaymentStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tech_challenge_fiap.util.converter.OrderConverter.orderToEntity;

@Service
@RequiredArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderRequestDto orderRequestDTO) {
        var payment = Payment.builder()
                .status(PaymentStatusEnum.WAITING_PAYMENT)
                .build();
       var order = Order.builder()
               .id(orderRequestDTO.getId())
               .payment(payment)
               .build();

        return orderRepository.save(orderToEntity(order));
    }

    @Override
    public Order updatePaymentStatus(String orderId, PaymentStatusDto paymentStatusDto) {
        var order = orderRepository.getOrderById(orderId);

        var payment = Payment.builder()
                .id(order.getPayment().getId())
                .qrCode(order.getPayment().getQrCode())
                .qrImage(order.getPayment().getQrImage())
                .status(PaymentStatusEnum.valueOf(paymentStatusDto.name()))
                .build();

        order.setPayment(payment);

        return orderRepository.save(orderToEntity(order));
    }
}
