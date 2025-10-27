package com.tech_challenge_fiap.services;

import com.tech_challenge_fiap.converter.PaymentConverter;
import com.tech_challenge_fiap.converter.ProductConverter;
import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.domains.product.CategoryEnum;
import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.dtos.internal.OrderRequestDto;
import com.tech_challenge_fiap.entities.OrderEntity;
import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.entities.ProductEntity;
import com.tech_challenge_fiap.http.clients.client.ClientClient;
import com.tech_challenge_fiap.http.clients.product.ProductClient;
import com.tech_challenge_fiap.repositories.order.OrderRepository;
import com.tech_challenge_fiap.services.order.OrderServiceImpl;
import com.tech_challenge_fiap.services.payment.PaymentService;
import com.tech_challenge_fiap.utils.exceptions.OrderNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class OrderServiceImplTest {

    private static final Product genericProduct = Product.builder()
            .id(UUID.randomUUID())
            .name("Test")
            .description("Test")
            .image("Test")
            .price(1L)
            .priceForClient(null)
            .category(CategoryEnum.ACOMPANHAMENTO)
            .quantity(1L)
            .build();

    private static final Payment gerenicPayment = Payment.builder()
            .id(UUID.randomUUID())
            .qrImage("Test")
            .qrCode("Test")
            .status(PaymentStatusEnum.CREATED)
            .build();

    private static final Client gerenicClient = Client.builder()
            .id(UUID.randomUUID())
            .name("Test")
            .cpf("111.111.111-11")
            .email("mail@mail.com.br")
            .build();

    @Mock
    private ClientClient clientClient;
    @Mock
    private ProductClient productClient;
    @Mock
    private PaymentService paymentService;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void shouldCreateOrderSuccessfullyWithClientNull() {
        when(clientClient.getClientById(any())).thenReturn(gerenicClient);
        when(productClient.getProductbyId(any())).thenReturn(genericProduct);
        when(paymentService.createPayment(any())).thenReturn(gerenicPayment);
        var orderEntity = OrderEntity.builder()
                .id(UUID.randomUUID())
                .status(OrderStatusEnum.CREATED)
                .createdAt(LocalDateTime.now())
                .client(null)
                .products(List.of(ProductConverter.toEntity(genericProduct)))
                .payment(PaymentConverter.toEntity(gerenicPayment))
                .build();

        when(orderRepository.save(any())).thenReturn(orderEntity);

        var request = OrderRequestDto.builder()
                .clientId(UUID.randomUUID())
                .productIds(List.of(UUID.randomUUID()))
                .build();

        orderService.createOrder(request);

        verify(clientClient, times(1)).getClientById(any());
        verify(productClient, times(1)).getProductbyId(any());
        verify(paymentService, times(1)).createPayment(any());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void shouldCreateOrderSuccessfullyWithoutClientNull() {
        when(productClient.getProductbyId(any())).thenReturn(genericProduct);
        when(paymentService.createPayment(any())).thenReturn(gerenicPayment);
        var orderEntity = OrderEntity.builder()
                .id(UUID.randomUUID())
                .status(OrderStatusEnum.CREATED)
                .createdAt(LocalDateTime.now())
                .client(null)
                .products(List.of(ProductConverter.toEntity(genericProduct)))
                .payment(PaymentConverter.toEntity(gerenicPayment))
                .build();

        when(orderRepository.save(any())).thenReturn(orderEntity);

        var request = OrderRequestDto.builder()
                .clientId(null)
                .productIds(List.of(UUID.randomUUID()))
                .build();

        orderService.createOrder(request);

        verify(clientClient, times(0)).getClientById(any());
        verify(productClient, times(1)).getProductbyId(any());
        verify(paymentService, times(1)).createPayment(any());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void shoudFindAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated() {
        var paymentEntity = PaymentEntity.builder()
                .id(UUID.randomUUID())
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var productEntity = ProductEntity.builder()
                .id(UUID.randomUUID())
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var orderEntity = OrderEntity.builder()
                .id(UUID.randomUUID())
                .status(OrderStatusEnum.CREATED)
                .client(null)
                .products(List.of(productEntity))
                .payment(paymentEntity)
                .createdAt(LocalDateTime.now())
                .build();

        when(orderRepository.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated())
                .thenReturn(List.of(orderEntity));

        orderService.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();

        verify(orderRepository, times(1)).findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
    }

    @Test
    void shouldTUpdateStatusOfOrderSuccessfully() {
        var paymentEntity = PaymentEntity.builder()
                .id(UUID.randomUUID())
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var productEntity = ProductEntity.builder()
                .id(UUID.randomUUID())
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var orderEntity = OrderEntity.builder()
                .id(UUID.randomUUID())
                .status(OrderStatusEnum.CREATED)
                .client(null)
                .products(List.of(productEntity))
                .payment(paymentEntity)
                .createdAt(LocalDateTime.now())
                .build();

        when(orderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        when(orderRepository.save(any())).thenReturn(orderEntity);

        assertDoesNotThrow(() -> {
            orderService.updateStatus(orderEntity.getId(), OrderStatusEnum.FINALIZED);
        });

        verify(orderRepository, times(1)).findById(any());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowOrderNotFoundExceptionWhenUpdateStatusNotFoundOrder() {
        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.updateStatus(any(), OrderStatusEnum.FINALIZED));
        verify(orderRepository, times(1)).findById(any());
        verify(orderRepository, times(0)).save(any());
    }

    @Test
    void shouldUpdatePaymentStatusSuccessfully() {
        doNothing().when(paymentService).updatePaymentStatus(any(), any());

        assertDoesNotThrow(() -> {
            orderService.updatePaymentStatus(any(), PaymentStatusEnum.PAID);
        });

        verify(paymentService, times(1)).updatePaymentStatus(any(), any());
    }
}
