package com.tech_challenge_fiap.services.payment;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.http.clients.payment.PaymentClient;
import com.tech_challenge_fiap.http.clients.payment.request.PayerRequestDto;
import com.tech_challenge_fiap.http.clients.payment.request.PaymentRequestDto;
import com.tech_challenge_fiap.http.clients.payment.response.PaymentResponseDto;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.utils.exceptions.CouldNotCreatePaymentException;
import com.tech_challenge_fiap.utils.exceptions.PaymentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tech_challenge_fiap.converter.PaymentConverter.toDomain;
import static com.tech_challenge_fiap.converter.PaymentConverter.toEntity;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentClient paymentClient;

    @Override
    public Payment createPayment(Order order) {
        try {
            PaymentResponseDto paymentResponseDto = requestCreatePayment(order);
            Payment payment = toDomain(paymentResponseDto);
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

    private PaymentResponseDto requestCreatePayment(Order order) {
        PayerRequestDto payer = PayerRequestDto.builder()
                .email(nonNull(order.getClient()) ? order.getClient().getEmail() : null)
                .firstName(nonNull(order.getClient()) ? order.getClient().getName() : null)
                .build();
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .amount(order.getOrderPrice())
                .paymentMethod("PIX")
                .payer(payer)
                .build();
        return paymentClient.createPayment(paymentRequestDto);
    }
}
