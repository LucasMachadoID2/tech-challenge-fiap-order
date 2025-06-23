package com.tech_challenge_fiap.usecases.order;

import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentRequestDto;
import com.tech_challenge_fiap.adapters.OrderAdapter;
import com.tech_challenge_fiap.core.domain.client.Client;
import com.tech_challenge_fiap.core.domain.client.ClientUseCase;
import com.tech_challenge_fiap.core.domain.payment.Payment;
import com.tech_challenge_fiap.core.domain.payment.PaymentRepository;
import com.tech_challenge_fiap.core.domain.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.core.domain.product.Product;
import com.tech_challenge_fiap.core.domain.product.ProductUseCase;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import com.tech_challenge_fiap.gateways.order.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tech_challenge_fiap.adapters.OrderAdapter.orderEntityToDataModel;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderGateway orderGateway;
    private final ClientUseCase clientUseCase;
    private final ProductUseCase productUseCase;
    private final PaymentRepository paymentRepository;

    @Override
    public OrderEntity createOrder(OrderRequestDto orderRequestDto) {
        Client client = findClientOrNull(orderRequestDto.getClientId());

        List<Product> products =
                orderRequestDto.getProductIds().stream().map(productUseCase::findById).toList();

        OrderEntity orderEntity = OrderEntity.builder()
                .status(OrderEntityStatusEnum.CREATED)
                .client(client)
                .products(products)
                .build();

        Payment payment = paymentRepository.createPayment(orderEntity);

        orderEntity.setPayment(payment);

        return orderGateway.save(orderEntityToDataModel(orderEntity));
    }

    @Override
    public OrderEntity updatePaymentStatus(PaymentRequestDto paymentRequestDto) {
        var order = orderGateway.getOrderById(paymentRequestDto.getOrderId());

        PaymentStatusEnum paymentStatusEnum = PaymentStatusEnum.safeValueOf(paymentRequestDto.getStatus());
        order.getPayment().setStatus(paymentStatusEnum);

        return orderGateway.save(orderEntityToDataModel(order));
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderGateway.findAll();
    }

    @Override
    public OrderEntity updateStatus(String orderId, OrderEntityStatusEnum status) {
        OrderEntity orderEntityFound = orderGateway.getOrderById(orderId);

        orderEntityFound.setStatus(status);

        return orderGateway.save(OrderAdapter.orderEntityToDataModel(orderEntityFound));
    }

    private Client findClientOrNull(String clientId) {
        if (nonNull(clientId)) {
           return clientUseCase.findById(clientId);
        }

        return null;
    }
}
