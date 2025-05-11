package com.tech_challenge_fiap.adapter.service.outbound.repository;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.payment.PaymentRepository;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentResponseDto;
import com.tech_challenge_fiap.util.exception.CouldNotCreatePaymentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static com.tech_challenge_fiap.util.converter.PaymentConverter.toPaymentResponseDTO;

@Component
public class MercadoPagoPaymentRepository implements PaymentRepository {

    @Value("${pix.expiration.time.minutes}")
    private String pixExpirationTimeMinutes;

    @Override
    public PaymentResponseDto createPayment(Order order) {

        try {
            PaymentClient paymentClient = new PaymentClient();

            PaymentPayerRequest paymentPayerRequest = PaymentPayerRequest.builder()
                    .email("teste@teste.com.br")
                    .firstName("LucasTeste")
                    .build();

            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(BigDecimal.valueOf(123))
                    .payer(paymentPayerRequest)
                    .dateOfExpiration(OffsetDateTime.now().plusMinutes(Long.parseLong(pixExpirationTimeMinutes))
                            .withOffsetSameInstant(ZoneOffset.of("-03:00")))
                    .paymentMethodId("pix")
                    .build();

            Payment payment = paymentClient.create(paymentCreateRequest);

            return toPaymentResponseDTO(payment);
        } catch (MPException | MPApiException ex) {
            ex.printStackTrace();
            throw new CouldNotCreatePaymentException(order.getId());
        }
    }
}
