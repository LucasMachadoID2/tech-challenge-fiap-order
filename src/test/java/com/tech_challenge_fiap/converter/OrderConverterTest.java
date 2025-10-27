package com.tech_challenge_fiap.converter;

import com.tech_challenge_fiap.domains.client.Client;
import com.tech_challenge_fiap.domains.order.Order;
import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.domains.payment.Payment;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.domains.product.CategoryEnum;
import com.tech_challenge_fiap.domains.product.Product;
import com.tech_challenge_fiap.entities.ClientEntity;
import com.tech_challenge_fiap.entities.OrderEntity;
import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class OrderConverterTest {

    @Test
    void shouldConvertToDomainFromEntityWithNullClientAndPayment() {
        var productEntity = ProductEntity.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var orderEntity = OrderEntity.builder()
                .id(1L)
                .status(OrderStatusEnum.CREATED)
                .client(null)
                .products(List.of(productEntity))
                .payment(null)
                .createdAt(LocalDateTime.now())
                .build();

        var orderDomain = OrderConverter.toDomain(orderEntity);

        assertEquals(orderEntity.getId(), orderDomain.getId());
        assertEquals(orderEntity.getStatus(), orderDomain.getStatus());
        assertNull(orderDomain.getClient());
        assertEquals(1, orderDomain.getProducts().size());
        assertNull(orderDomain.getPayment());
    }

    @Test
    void shouldConvertToDomainFromEntityWithClientAndPayment() {
        var clientEntity = ClientEntity.builder()
                .id(1L)
                .name("Test")
                .cpf("123.456.789-00")
                .email("mail@mail.com.br")
                .build();

        var paymentEntity = PaymentEntity.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var productEntity = ProductEntity.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var orderEntity = OrderEntity.builder()
                .id(1L)
                .status(OrderStatusEnum.CREATED)
                .client(clientEntity)
                .products(List.of(productEntity))
                .payment(paymentEntity)
                .createdAt(LocalDateTime.now())
                .build();

        var orderDomain = OrderConverter.toDomain(orderEntity);

        assertEquals(orderEntity.getId(), orderDomain.getId());
        assertEquals(orderEntity.getStatus(), orderDomain.getStatus());
        assertNotNull(orderDomain.getClient());
        assertEquals(1, orderDomain.getProducts().size());
        assertNotNull(orderDomain.getPayment());
    }

    @Test
    void shouldConvertToEntityFromDomainWithNullClientAndPayment() {
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
                .client(null)
                .products(List.of(product))
                .payment(null)
                .createdAt(LocalDateTime.now())
                .build();

        var orderEntity = OrderConverter.toEntity(order);

        assertEquals(order.getId(), orderEntity.getId());
        assertEquals(order.getStatus(), orderEntity.getStatus());
        assertNull(orderEntity.getClient());
        assertEquals(1, orderEntity.getProducts().size());
        assertNull(orderEntity.getPayment());
    }

    @Test
    void shouldConvertToEntityFromDomainWithClientAndPayment() {
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

        var orderEntity = OrderConverter.toEntity(order);

        assertEquals(order.getId(), orderEntity.getId());
        assertEquals(order.getStatus(), orderEntity.getStatus());
        assertNotNull(orderEntity.getClient());
        assertEquals(1, orderEntity.getProducts().size());
        assertNotNull(orderEntity.getPayment());
    }

    @Test
    void shouldConvertToResponseFromDomainWithoutClient() {
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
                .client(null)
                .products(List.of(product))
                .payment(payment)
                .createdAt(LocalDateTime.now())
                .build();

        var response = OrderConverter.toResponse(order);

        assertEquals(order.getId(), response.getId());
        assertEquals(order.getStatus().name(), response.getStatus());
        assertNull(response.getClient());
        assertEquals(1, response.getProducts().size());
        assertNotNull(response.getPayment());
    }

    @Test
    void shouldConvertToResponseFromDomainWithClientAndPayment() {
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

        var response = OrderConverter.toResponse(order);

        assertEquals(order.getId(), response.getId());
        assertEquals(order.getStatus().name(), response.getStatus());
        assertNotNull(response.getClient());
        assertEquals(1, response.getProducts().size());
        assertNotNull(response.getPayment());
    }

    @Test
    void shouldConvertToResponseFromEntityWithoutClient() {
        var paymentEntity = PaymentEntity.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var productEntity = ProductEntity.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var orderEntity = OrderEntity.builder()
                .id(1L)
                .status(OrderStatusEnum.CREATED)
                .client(null)
                .products(List.of(productEntity))
                .payment(paymentEntity)
                .createdAt(LocalDateTime.now())
                .build();

        var response = OrderConverter.toResponse(orderEntity);

        assertEquals(orderEntity.getId(), response.getId());
        assertEquals(orderEntity.getStatus().name(), response.getStatus());
        assertNull(response.getClient());
        assertEquals(1, response.getProducts().size());
        assertNotNull(response.getPayment());
    }

    @Test
    void shouldConvertToResponseFromDomainWithClient() {
        var clientEntity = ClientEntity.builder()
                .id(1L)
                .name("Test")
                .cpf("123.456.789-00")
                .email("mail@mail.com.br")
                .build();

        var paymentEntity = PaymentEntity.builder()
                .id(1L)
                .qrImage("Image base64")
                .qrCode("Image code")
                .status(PaymentStatusEnum.CREATED)
                .build();

        var productEntity = ProductEntity.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .image("product-image.jpg")
                .price(100L)
                .priceForClient(90L)
                .category(CategoryEnum.ACOMPANHAMENTO)
                .quantity(10L)
                .build();

        var orderEntity = OrderEntity.builder()
                .id(1L)
                .status(OrderStatusEnum.CREATED)
                .client(clientEntity)
                .products(List.of(productEntity))
                .payment(paymentEntity)
                .createdAt(LocalDateTime.now())
                .build();

        var response = OrderConverter.toResponse(orderEntity);

        assertEquals(orderEntity.getId(), response.getId());
        assertEquals(orderEntity.getStatus().name(), response.getStatus());
        assertNotNull(response.getClient());
        assertEquals(1, response.getProducts().size());
        assertNotNull(response.getPayment());
    }
}
