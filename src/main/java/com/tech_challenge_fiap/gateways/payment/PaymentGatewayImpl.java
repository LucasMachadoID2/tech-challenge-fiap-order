package com.tech_challenge_fiap.gateways.payment;

import com.tech_challenge_fiap.dtos.external.PaymentDTO;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.utils.exceptions.CouldNotCreatePaymentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.tech_challenge_fiap.adapters.PaymentAdapter.toEntity;


@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentGatewayImpl implements PaymentGateway {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentEntity createPayment(OrderEntity orderEntity) {
        try {
            log.info("Creating payment for order: {}", orderEntity.getId());

            PaymentDTO paymentDto = paymentRepository.createPayment(orderEntity);
            
            return toEntity(paymentDto);

        } catch (Exception e) {
            log.error("Failed to create payment for order: {}", orderEntity.getId(), e);
            throw new CouldNotCreatePaymentException(orderEntity.getId(), e);
        }
    }
}