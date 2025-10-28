package com.tech_challenge_fiap.cucumber.datatable;

import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.entities.PaymentEntity;
import io.cucumber.java.DataTableType;

import java.util.Map;
import java.util.UUID;

import static java.util.Objects.nonNull;

public class PaymentDataTableConfig {

    @DataTableType
    public PaymentEntity productEntry(Map<String, String> row) {
        return PaymentEntity.builder()
                .id(nonNull(row.get("id")) ? UUID.fromString(row.get("id")) : null)
                .qrImage(row.get("category"))
                .qrCode(row.get("name"))
                .status(nonNull(row.get("status")) ? PaymentStatusEnum.valueOf(row.get("status")) : null)
                .build();
    }
}
