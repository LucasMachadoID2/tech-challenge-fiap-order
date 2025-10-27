package com.tech_challenge_fiap.domains.order;

import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.domains.product.CategoryEnum;
import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.utils.exceptions.OrderProductsCantBeNullOrEmpty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class OrderTest {

    @Test
    void shouldCreateOrderWithoutClientAndPriceWithoutPromotion() {
        var product = Product.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .image("Test")
                .price(1L)
                .priceForClient(null)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(1L)
                .build();

        var payment = Payment.builder()
                .id(1L)
                .qrImage("Test")
                .qrCode("Test")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var order = Order.builder()
                .id(1L)
                .status(OrderStatusEnum.CREATED)
                .client(null)
                .products(List.of(product))
                .payment(payment)
                .createdAt(LocalDateTime.now())
                .build();

        assertEquals(product.getPrice(), order.getOrderPrice());
    }

    @Test
    void shouldCreateOrderWithClientAndPriceWithPromotion() {
        var client = Client.builder()
                .id(1L)
                .name("Test")
                .cpf("111.111.111-11")
                .email("mail@mail.com.br")
                .build();

        var product = Product.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .image("Test")
                .price(2L)
                .priceForClient(1L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(1L)
                .build();

        var payment = Payment.builder()
                .id(1L)
                .qrImage("Test")
                .qrCode("Test")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var order = Order.builder()
                .id(1L)
                .status(OrderStatusEnum.CREATED)
                .client(client)
                .products(List.of(product))
                .payment(payment)
                .createdAt(LocalDateTime.now())
                .build();

        assertEquals(product.getPriceForClient(), order.getOrderPrice());
    }

    @Test
    void shouldThrowOrderProductsCantBeNullOrEmptyWhenProductsAreNull() {
        var payment = Payment.builder()
                .id(1L)
                .qrImage("Test")
                .qrCode("Test")
                .status(PaymentStatusEnum.CREATED)
                .build();

        assertThrows(OrderProductsCantBeNullOrEmpty.class, () -> {
            Order.builder()
                    .id(1L)
                    .status(OrderStatusEnum.CREATED)
                    .client(null)
                    .products(null)
                    .payment(payment)
                    .createdAt(LocalDateTime.now())
                    .build();
        });
    }

    @Test
    void shouldThrowOrderProductsCantBeNullOrEmptyWhenProductsAreEmpty() {
        var payment = Payment.builder()
                .id(1L)
                .qrImage("Test")
                .qrCode("Test")
                .status(PaymentStatusEnum.CREATED)
                .build();

        assertThrows(OrderProductsCantBeNullOrEmpty.class, () -> {
            Order.builder()
                    .id(1L)
                    .status(OrderStatusEnum.CREATED)
                    .client(null)
                    .products(List.of())
                    .payment(payment)
                    .createdAt(LocalDateTime.now())
                    .build();
        });
    }
}
