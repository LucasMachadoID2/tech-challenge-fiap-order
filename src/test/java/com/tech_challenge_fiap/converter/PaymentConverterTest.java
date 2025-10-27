package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.entities.PaymentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class PaymentConverterTest {

    @Test
    void shouldConvertToDomainFromEntity() {
        var entity = PaymentEntity.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var payment = PaymentConverter.toDomain(entity);

        assertEquals(entity.getId(), payment.getId());
        assertEquals(entity.getQrImage(), payment.getQrImage());
        assertEquals(entity.getQrCode(), payment.getQrCode());
        assertEquals(entity.getStatus(), payment.getStatus());
    }

    @Test
    void shouldConvertToEntityFromDomain() {
        var payment = Payment.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var entity = PaymentConverter.toEntity(payment);

        assertEquals(payment.getId(), entity.getId());
        assertEquals(payment.getQrImage(), entity.getQrImage());
        assertEquals(payment.getQrCode(), entity.getQrCode());
        assertEquals(payment.getStatus(), entity.getStatus());
    }

    @Test
    void shouldConvertToResponseFromDomain() {
        var payment = Payment.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var entity = PaymentConverter.toResponse(payment);

        assertEquals(payment.getId(), entity.getId());
        assertEquals(payment.getQrImage(), entity.getQrImage());
        assertEquals(payment.getQrCode(), entity.getQrCode());
        assertEquals(payment.getStatus().name(), entity.getStatus());
    }

    @Test
    void shouldConvertToDomainFromPaymentDTO() {
        // TODO: Implementa testes para quando receber o objeto do microserviço de pagamento
    }
}
