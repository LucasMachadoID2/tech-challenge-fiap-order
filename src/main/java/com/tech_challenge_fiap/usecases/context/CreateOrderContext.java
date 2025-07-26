package com.tech_challenge_fiap.usecases.context;

import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import com.tech_challenge_fiap.gateways.payment.PaymentGateway;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
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
    private PaymentGateway paymentGateway;
    private OrderGateway orderGateway;
}
