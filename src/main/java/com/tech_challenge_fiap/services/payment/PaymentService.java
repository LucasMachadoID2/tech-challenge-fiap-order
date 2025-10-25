package com.tech_challenge_fiap.services.payment;

import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;

public interface PaymentService {

    Payment createPayment(Order order);

    void updatePaymentStatus(Long paymentId, PaymentStatusEnum status);
}
