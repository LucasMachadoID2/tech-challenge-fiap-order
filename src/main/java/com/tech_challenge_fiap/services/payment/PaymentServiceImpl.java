package com.tech_challenge_fiap.services.payment;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.dtos.external.PaymentDTO;
import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.http.clients.payment.PaymentClient;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.utils.exceptions.CouldNotCreatePaymentException;
import com.tech_challenge_fiap.utils.exceptions.PaymentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tech_challenge_fiap.converter.PaymentConverter.toDomain;
import static com.tech_challenge_fiap.converter.PaymentConverter.toEntity;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentClient paymentClient;

    @Override
    public Payment createPayment(Order order) {
        try {
            PaymentDTO paymentDto = paymentClient.createPayment(order);
            Payment payment = toDomain(paymentDto);
            PaymentEntity savedPayment = paymentRepository.save(toEntity(payment));

            return toDomain(savedPayment);
        } catch (Exception e) {
            throw new CouldNotCreatePaymentException(order.getId(), e);
        }
    }

    @Override
    public void updatePaymentStatus(UUID paymentId, PaymentStatusEnum status) {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException(paymentId));
        paymentEntity.setStatus(status);
        paymentRepository.save(paymentEntity);
    }
}
