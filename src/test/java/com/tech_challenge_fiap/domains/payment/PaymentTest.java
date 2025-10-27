package com.tech_challenge_fiap.domains.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(SpringExtension.class)
public class PaymentTest {

    @Test
    void shouldCreatePaymentWithSuccess() {
        assertDoesNotThrow(() -> Payment.builder()
                .id(1L)
                .qrImage("Test")
                .qrCode("Test")
                .status(PaymentStatusEnum.CREATED)
                .build());
    }
}
