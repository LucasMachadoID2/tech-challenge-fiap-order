package com.tech_challenge_fiap.services;

import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.domains.product.CategoryEnum;
import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.dtos.external.PaymentDTO;
import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.services.payment.PaymentServiceImpl;
import com.tech_challenge_fiap.utils.exceptions.CouldNotCreatePaymentException;
import com.tech_challenge_fiap.utils.exceptions.PaymentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void shouldCreatePaymentSuccessfully() {
        var client = Client.builder()
                .id(1L)
                .name("Test")
                .cpf("123.456.789-00")
                .email("mail@mail.com.br")
                .build();

        var payment = Payment.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var order = Order.builder()
                .id(1L)
                .status(OrderStatusEnum.CREATED)
                .client(client)
                .products(List.of(product))
                .payment(payment)
                .createdAt(LocalDateTime.now())
                .build();

        var paymentDto = PaymentDTO.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status("CREATED")
                .build();

        var paymentEntity = PaymentEntity.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        when(paymentRepository.createPayment(order)).thenReturn(paymentDto);
        when(paymentRepository.save(any())).thenReturn(paymentEntity);

        assertDoesNotThrow(() -> {
            paymentService.createPayment(order);
        });

        verify(paymentRepository, times(1)).createPayment(any());
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowsCouldNotCreatePaymentExceptionWhenErrorOccur() {
        var client = Client.builder()
                .id(1L)
                .name("Test")
                .cpf("123.456.789-00")
                .email("mail@mail.com.br")
                .build();

        var payment = Payment.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var order = Order.builder()
                .id(1L)
                .status(OrderStatusEnum.CREATED)
                .client(client)
                .products(List.of(product))
                .payment(payment)
                .createdAt(LocalDateTime.now())
                .build();

        when(paymentRepository.createPayment(order)).thenThrow(CouldNotCreatePaymentException.class);

        assertThrows(CouldNotCreatePaymentException.class, () -> {
            paymentService.createPayment(order);
        });

        verify(paymentRepository, times(1)).createPayment(any());
        verify(paymentRepository, times(0)).save(any());
    }

    @Test
    void shouldUpdatePaymentStatusSuccessfully() {
        var paymentEntity = PaymentEntity.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(paymentEntity));
        when(paymentRepository.save(any())).thenReturn(paymentEntity);

        assertDoesNotThrow(() -> {
            paymentService.updatePaymentStatus(1L, PaymentStatusEnum.PAID);
        });

        verify(paymentRepository, times(1)).findById(any());
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowsPaymentNotFoundExceptionWhenNotFoundPayment() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.updatePaymentStatus(1L, PaymentStatusEnum.PAID);
        });

        verify(paymentRepository, times(1)).findById(any());
        verify(paymentRepository, times(0)).save(any());
    }
}
