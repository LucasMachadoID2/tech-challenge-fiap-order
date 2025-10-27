package com.tech_challenge_fiap.cucumber.order;

import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.entities.ClientEntity;
import com.tech_challenge_fiap.entities.OrderEntity;
import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.entities.ProductEntity;
import com.tech_challenge_fiap.repositories.client.ClientRepository;
import com.tech_challenge_fiap.repositories.order.OrderRepository;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.repositories.product.ProductRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderDefinitions {

    private static final String ID_KEY = "id";
    private static final String STATUS = "status";
    private static final String CLIENT_ID = "client";
    private static final String PRODUCT_ID_KEY = "productIds";
    private static final String PAYMENT_ID_KEY = "paymentId";
    private static final String CREATED_AT = "createdAt";
    private static final String LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    @Dado("^que exista pedidos cadastrados no sistema")
    @Transactional
    public void createOrders(DataTable dataTable) {
        List<Map<String, String>> linhas = dataTable.asMaps(String.class, String.class);

        linhas.forEach(it -> {

            ClientEntity client = getClient(it.get(CLIENT_ID));

            List<ProductEntity> products = getProducts(it.get(PRODUCT_ID_KEY));
            PaymentEntity payment = getPayment(UUID.fromString(it.get(PAYMENT_ID_KEY)));

            var order = OrderEntity.builder()
                    .id(UUID.fromString(it.get(ID_KEY)))
                    .status(OrderStatusEnum.valueOf(it.get(STATUS)))
                    .client(client)
                    .products(products)
                    .payment(payment)
                    .createdAt(
                            LocalDateTime.parse(it.get(CREATED_AT), DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT))
                    )
                    .build();

            orderRepository.save(order);
        });
    }

    private ClientEntity getClient(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            return null;
        }
        return clientRepository.findById(UUID.fromString(clientId)).orElseThrow();
    }

    private List<ProductEntity> getProducts(String productIds) {
        return productRepository.findAllById(Arrays.stream(productIds.split(",")).map(UUID::fromString).toList());
    }

    private PaymentEntity getPayment(UUID paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow();
    }

}
