package com.tech_challenge_fiap.repositories.payment;

import com.tech_challenge_fiap.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>, PaymentRepositoryCustom {
}
