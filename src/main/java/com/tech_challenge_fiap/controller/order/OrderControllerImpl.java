package com.tech_challenge_fiap.controller.order;

import com.tech_challenge_fiap.adapters.OrderAdapter;
import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.dtos.OrderResponseDto;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import com.tech_challenge_fiap.gateways.product.ProductGateway;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.usecases.CreateOrderUseCase;
import com.tech_challenge_fiap.usecases.OrderUseCase;
import com.tech_challenge_fiap.usecases.context.CreateOrderContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderControllerImpl implements OrderController {

    private final ClientGateway clientGateway;
    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;
    private final PaymentRepository paymentRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDTO) {
        CreateOrderContext createOrderContext = CreateOrderContext.builder()
                .clientId(orderRequestDTO.getClientId())
                .productIds(orderRequestDTO.getProductIds())
                .clientGateway(clientGateway)
                .productGateway(productGateway)
                .paymentRepository(paymentRepository)
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
