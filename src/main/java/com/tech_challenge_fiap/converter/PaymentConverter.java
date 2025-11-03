package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.dtos.external.PaymentDto;
import com.tech_challenge_fiap.dtos.internal.PaymentResponseDto;
import com.tech_challenge_fiap.entities.PaymentEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentConverter {

    public static Payment toDomain(PaymentEntity paymentEntity) {
        return Payment.builder()
                .id(paymentEntity.getId())
                .qrImage(paymentEntity.getQrImage())
                .qrCode(paymentEntity.getQrCode())
                .status(paymentEntity.getStatus())
                .paymentId(paymentEntity.getPaymentId())
                .build();
    }

    public static PaymentEntity toEntity(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId())
                .qrImage(payment.getQrImage())
                .qrCode(payment.getQrCode())
                .status(payment.getStatus())
                .paymentId(payment.getPaymentId())
                .build();
    }

    public static PaymentResponseDto toResponse(Payment payment) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .qrImage(payment.getQrImage())
                .qrCode(payment.getQrCode())
                .status(payment.getStatus().name())
                .paymentId(payment.getPaymentId())
                .build();
    }


    public static PaymentResponseDto toResponse(PaymentEntity payment) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .qrImage(payment.getQrImage())
                .qrCode(payment.getQrCode())
                .status(payment.getStatus().name())
                .paymentId(payment.getPaymentId())
                .build();
    }

    public static Payment toDomain(PaymentDto paymentDTO) {
        return Payment.builder()
                .id(paymentDTO.getId())
                .qrImage(paymentDTO.getQrImage())
                .qrCode(paymentDTO.getQrCode())
                .status(PaymentStatusEnum.valueOf(paymentDTO.getStatus()))
                .paymentId(paymentDTO.getPaymentId())
                .build();
    }

    public static Payment toDomain(com.tech_challenge_fiap.http.clients.payment.response.PaymentResponseDto paymentResponseDto) {
        return Payment.builder()
                .qrImage(paymentResponseDto.getQrImage())
                .qrCode(paymentResponseDto.getQrCode())
                .status(PaymentStatusEnum.valueOf(paymentResponseDto.getStatus()))
                .paymentId(paymentResponseDto.getId())
                .build();
    }
}