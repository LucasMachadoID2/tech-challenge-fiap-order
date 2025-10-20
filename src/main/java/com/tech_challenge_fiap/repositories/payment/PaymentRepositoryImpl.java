package com.tech_challenge_fiap.repositories.payment;

import com.tech_challenge_fiap.dtos.external.PaymentDTO;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    @Override
    public PaymentDTO createPayment(OrderEntity orderEntity) {
       // criar payment
        return new PaymentDTO();
    }
}
