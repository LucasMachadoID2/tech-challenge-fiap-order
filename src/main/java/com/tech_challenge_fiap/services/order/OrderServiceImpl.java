package com.tech_challenge_fiap.services.order;

import com.tech_challenge_fiap.converter.OrderConverter;
import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.dtos.internal.OrderRequestDto;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;
import com.tech_challenge_fiap.entities.OrderEntity;
import com.tech_challenge_fiap.repositories.client.ClientRepository;
import com.tech_challenge_fiap.repositories.order.OrderRepository;
import com.tech_challenge_fiap.repositories.product.ProductRepository;
import com.tech_challenge_fiap.services.payment.PaymentService;
import com.tech_challenge_fiap.utils.exceptions.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.tech_challenge_fiap.converter.OrderConverter.toDomain;
import static com.tech_challenge_fiap.converter.OrderConverter.toEntity;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final PaymentService paymentService;
    private final OrderRepository orderRepository;

    @Override
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDTO) {
        var order = createOrder(orderRequestDTO.getClientId(), orderRequestDTO.getProductIds());
        return OrderConverter.toResponse(order);
    }

    @Override
    public List<OrderResponseDto> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated() {
        List<OrderEntity> orderEntities = orderRepository.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
        return orderEntities.stream().map(OrderConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto updateStatus(Long orderId, OrderStatusEnum status) {
        OrderEntity orderFound = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        orderFound.setStatus(status);

        var orderSaved = orderRepository.save(orderFound);
        var order = toDomain(orderSaved);
        return OrderConverter.toResponse(order);
    }

    public void updatePaymentStatus(Long paymentId, PaymentStatusEnum status) {
        paymentService.updatePaymentStatus(paymentId, status);
    }

    private Order createOrder(Long clientId, List<Long> productIds) {
        Client client = findClientOrNull(clientId);

        List<Product> productEntities = productIds.stream().map(productRepository::getProductbyId).toList();

        Order order = Order.builder()
                .status(OrderStatusEnum.CREATED)
                .client(client)
                .products(productEntities)
                .createdAt(LocalDateTime.now())
                .build();

        Payment payment = paymentService.createPayment(order);

        order.setPayment(payment);

        var orderSaved = orderRepository.save(toEntity(order));

        return toDomain(orderSaved);
    }

    private Client findClientOrNull(Long clientId) {
        if (nonNull(clientId)) {
            return clientRepository.getClientById(clientId);
        }

        return null;
    }
}
