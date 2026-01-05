package com.tech_challenge_fiap.services.payment;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;

import java.util.UUID;

public interface PaymentService {

    Payment createPayment(Order order);

    void updatePaymentStatus(String paymentId, PaymentStatusEnum status);
}
