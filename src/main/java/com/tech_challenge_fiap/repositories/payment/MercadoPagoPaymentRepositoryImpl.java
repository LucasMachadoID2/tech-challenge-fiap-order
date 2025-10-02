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
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static java.util.Objects.nonNull;

@Slf4j
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
        log.info("c={}, valor={}, cliente={}",
            orderEntity.getId(),
            orderEntity.getOrderPrice(),
            nonNull(orderEntity.getClientEntity()) ? orderEntity.getClientEntity().getEmail() : "SEM CLIENTE"
        );


        try {
            PaymentClient paymentClient = new PaymentClient();
            PaymentPayerRequest paymentPayerRequest = createPaymentPayerRequest(orderEntity);

            log.info("[MercadoPago] Payer -> email={}, firstName={}, cpf={}",
                paymentPayerRequest.getEmail(),
                paymentPayerRequest.getFirstName(),
                paymentPayerRequest.getIdentification().getNumber()
            );

            PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                    .transactionAmount(BigDecimal.valueOf(orderEntity.getOrderPrice()))
                    .payer(paymentPayerRequest)
                    .dateOfExpiration(OffsetDateTime.now().plusMinutes(Long.parseLong(pixExpirationTimeMinutes))
                            .withOffsetSameInstant(ZoneOffset.of(TIMEZONE_BR)))
                    .paymentMethodId(PAYMENT_METHOD_PIX)
                    .build();

            log.info("[MercadoPago] PaymentCreateRequest: transactionAmount={}, expiration={}",
                    paymentCreateRequest.getTransactionAmount(),
                    paymentCreateRequest.getDateOfExpiration()
            );


            Payment payment = paymentClient.create(paymentCreateRequest);
            log.info("[MercadoPago] Payment criado com sucesso! paymentId={}, status={}",
                payment.getId(), payment.getStatus()
            );

            return PaymentAdapter.toEntity(payment);
        } catch (MPApiException ex) {
            log.error("[MercadoPago] API ERROR orderId={} -> statusCode={}, response={}",
                orderEntity.getId(),
                ex.getStatusCode(),
                ex.getApiResponse().getContent(),
                ex
            );
            throw new CouldNotCreatePaymentException(orderEntity.getId(), ex);

        } catch (MPException ex) {
            log.error("[MercadoPago] SDK ERROR orderId={}", orderEntity.getId(), ex);
            throw new CouldNotCreatePaymentException(orderEntity.getId(), ex);
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
