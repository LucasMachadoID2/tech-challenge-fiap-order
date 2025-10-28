package com.tech_challenge_fiap.cucumber.payment;

import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class PaymentDefinitions {

    private final PaymentRepository paymentRepository;

    @Dado("^que exista pagamentos cadastrados no sistema")
    @Transactional
    public void createPayments(DataTable dataTable) {
        paymentRepository.saveAll(dataTable.asList(PaymentEntity.class));
    }
}
