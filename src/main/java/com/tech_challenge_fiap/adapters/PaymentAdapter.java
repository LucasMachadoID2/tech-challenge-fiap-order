package com.tech_challenge_fiap.adapters;

import com.tech_challenge_fiap.data.models.PaymentDataModel;
import com.tech_challenge_fiap.dtos.PaymentResponseDto;
import com.tech_challenge_fiap.entities.payment.PaymentEntity;
import com.tech_challenge_fiap.entities.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.utils.enums.PaymentStatusEnumEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentAdapter {

    public static PaymentEntity toEntity(com.mercadopago.resources.payment.Payment payment) {
        return PaymentEntity.builder()
                .qrImage(payment.getPointOfInteraction().getTransactionData().getQrCodeBase64())
                .qrCode(payment.getPointOfInteraction().getTransactionData().getQrCode())
                .status(PaymentStatusEnum.WAITING_PAYMENT)
                .build();
    }

    public static PaymentEntity toEntity(PaymentDataModel paymentDataModel) {
        return PaymentEntity.builder()
                .id(paymentDataModel.getId())
                .qrImage(paymentDataModel.getQrImage())
                .qrCode(paymentDataModel.getQrCode())
                .status(PaymentStatusEnum.valueOf(paymentDataModel.getStatus().name()))
                .build();
    }

    public static PaymentDataModel toDataModel(PaymentEntity paymentEntity) {
        return PaymentDataModel.builder()
                .id(paymentEntity.getId())
                .qrImage(paymentEntity.getQrImage())
                .qrCode(paymentEntity.getQrCode())
                .status(PaymentStatusEnumEntity.valueOf(paymentEntity.getStatus().name()))
                .build();
    }

    public static PaymentResponseDto toResponse(PaymentEntity paymentEntity) {
        return PaymentResponseDto.builder()
                .id(paymentEntity.getId())
                .qrImage(paymentEntity.getQrImage())
                .qrCode(paymentEntity.getQrCode())
                .status(paymentEntity.getStatus().name())
                .build();
    }
}
