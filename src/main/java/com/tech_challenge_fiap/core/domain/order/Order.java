package com.tech_challenge_fiap.core.domain.order;

import com.tech_challenge_fiap.core.domain.payment.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Order {

    private String id;
    private PaymentStatus paymentStatus;
}
