package com.tech_challenge_fiap.usecases.context;

import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateOrderContext {

    private String clientId;
    private List<String> productIds;
    private ClientGateway clientGateway;
    private ProductGateway productGateway;
    private PaymentRepository paymentRepository;
    private OrderGateway orderGateway;
}
