package com.tech_challenge_fiap.controller.order;

import com.tech_challenge_fiap.adapters.OrderAdapter;
import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.dtos.OrderResponseDto;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import com.tech_challenge_fiap.gateways.payment.PaymentGateway;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.usecases.CreateOrderUseCase;
import com.tech_challenge_fiap.usecases.OrderUseCase;
import com.tech_challenge_fiap.usecases.context.CreateOrderContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderControllerImpl implements OrderController {

    private final ClientGateway clientGateway;
    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;
    private final PaymentGateway paymentGateway;

    @Override
    public OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto orderRequestDTO) {
        log.info("[Controller] Recebido DTO: client={}, products={}, ",
                orderRequestDTO.getClientId(), orderRequestDTO.getProductIds());
        CreateOrderContext createOrderContext = CreateOrderContext.builder()
                .clientId(orderRequestDTO.getClientId())
                .productIds(orderRequestDTO.getProductIds())
                .clientGateway(clientGateway)
                .productGateway(productGateway)
                .paymentGateway(paymentGateway)
                .orderGateway(orderGateway)
                .build();

        var order = CreateOrderUseCase.createOrder(createOrderContext);
        return OrderAdapter.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated() {
        var orders = OrderUseCase.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated(orderGateway);
        return orders.stream().map(OrderAdapter::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateStatus(String orderId, OrderEntityStatusEnum status) {
        var order = OrderUseCase.updateStatus(orderId, status, orderGateway);
        return OrderAdapter.toResponse(order);
    }
}
