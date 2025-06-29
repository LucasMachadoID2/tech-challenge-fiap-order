package com.tech_challenge_fiap.repositories.payment;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.tech_challenge_fiap.adapters.PaymentAdapter;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.utils.exceptions.CouldNotCreatePaymentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static java.util.Objects.nonNull;

@Component
public class MercadoPagoPaymentRepositoryImpl implements PaymentRepository {

    @Value("${pix.expiration.time.minutes}")
    private String pixExpirationTimeMinutes;

    private final static String IDENTIFICATION_CPF = "CPF";
    private final static String TIMEZONE_BR = "-03:00";
    private final static String PAYMENT_METHOD_PIX = "pix";
    private final static String CPF_USER_TEST = "85713983056";
    private final static String MAIL_USER_TEST = "mail@mail.com";
    private final static String NAME_USER_TEST = "User Test";

    @Override
    public PaymentEntity createPayment(OrderEntity orderEntity) {

        try {
            PaymentClient paymentClient = new PaymentClient();
            PaymentPayerRequest paymentPayerRequest = createPaymentPayerRequest(orderEntity);

            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(BigDecimal.valueOf(orderEntity.getOrderPrice()))
                    .payer(paymentPayerRequest)
                    .dateOfExpiration(OffsetDateTime.now().plusMinutes(Long.parseLong(pixExpirationTimeMinutes))
                            .withOffsetSameInstant(ZoneOffset.of(TIMEZONE_BR)))
                    .paymentMethodId(PAYMENT_METHOD_PIX)
                    .build();

            Payment payment = paymentClient.create(paymentCreateRequest);

            return PaymentAdapter.toEntity(payment);
        } catch (MPException | MPApiException ex) {
            throw new CouldNotCreatePaymentException(orderEntity.getId());
        }
    }

    private PaymentPayerRequest createPaymentPayerRequest(OrderEntity orderEntity) {
        if (nonNull(orderEntity.getClientEntity())) {
            return PaymentPayerRequest.builder()
                    .email(orderEntity.getClientEntity().getEmail())
                    .firstName(orderEntity.getClientEntity().getName())
                    .identification(
                            IdentificationRequest.builder()
                                    .type(IDENTIFICATION_CPF)
                                    .number(orderEntity.getClientEntity().getCpf())
                                    .build()
                    )
                    .build();
        }

        return PaymentPayerRequest.builder()
                .email(MAIL_USER_TEST)
                .firstName(NAME_USER_TEST)
                .identification(
                        IdentificationRequest.builder()
                                .type(IDENTIFICATION_CPF)
                                .number(CPF_USER_TEST)
                                .build()
                )
                .build();
    }
}
