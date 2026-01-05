package com.tech_challenge_fiap.repositories.payment;

import com.tech_challenge_fiap.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {

    @Query("SELECT p FROM PaymentEntity p WHERE p.paymentId = :paymentId")
    PaymentEntity findByPaymentId(String paymentId);
}
