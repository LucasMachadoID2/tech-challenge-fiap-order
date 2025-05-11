package com.tech_challenge_fiap.util.converter;

import com.mercadopago.resources.payment.Payment;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentConverter {

    public static PaymentResponseDto toPaymentResponseDTO(Payment payment) {
        return PaymentResponseDto.builder()
                .qrCodeImage(payment.getPointOfInteraction().getTransactionData().getQrCodeBase64())
                .qrCode(payment.getPointOfInteraction().getTransactionData().getQrCode())
                .build();
    }
}
