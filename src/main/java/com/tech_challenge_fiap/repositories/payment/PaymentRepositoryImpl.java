package com.tech_challenge_fiap.repositories.payment;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.dtos.external.PaymentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryCustom {

    @Override
    public PaymentDTO createPayment(Order order) {
        // TODO: request para o microserviço de pagamentos
        return new PaymentDTO();
    }
}
