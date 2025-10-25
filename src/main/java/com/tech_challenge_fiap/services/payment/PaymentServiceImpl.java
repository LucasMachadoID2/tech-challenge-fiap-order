package com.tech_challenge_fiap.services.payment;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.dtos.external.PaymentDTO;
import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.utils.exceptions.CouldNotCreatePaymentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tech_challenge_fiap.converter.PaymentAdapter.toDomain;
import static com.tech_challenge_fiap.converter.PaymentAdapter.toEntity;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Order order) {
        try {
            PaymentDTO paymentDto = paymentRepository.createPayment(order);
            Payment payment = toDomain(paymentDto);
            PaymentEntity savedPayment = paymentRepository.save(toEntity(payment));

            return toDomain(savedPayment);
        } catch (Exception e) {
            throw new CouldNotCreatePaymentException(order.getId(), e);
        }
    }

    @Override
    public void updatePaymentStatus(Long paymentId, PaymentStatusEnum status) {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId).orElseThrow();
        paymentEntity.setStatus(status);
        paymentRepository.save(paymentEntity);
    }
}
