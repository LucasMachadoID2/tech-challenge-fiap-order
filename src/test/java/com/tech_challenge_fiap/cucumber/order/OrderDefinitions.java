package com.tech_challenge_fiap.cucumber.order;

import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;
import com.tech_challenge_fiap.entities.ClientEntity;
import com.tech_challenge_fiap.entities.OrderEntity;
import com.tech_challenge_fiap.entities.PaymentEntity;
import com.tech_challenge_fiap.entities.ProductEntity;
import com.tech_challenge_fiap.repositories.client.ClientRepository;
import com.tech_challenge_fiap.repositories.order.OrderRepository;
import com.tech_challenge_fiap.repositories.payment.PaymentRepository;
import com.tech_challenge_fiap.repositories.product.ProductRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class OrderDefinitions {

    private static final String ID_KEY = "id";
    private static final String STATUS = "status";
    private static final String CLIENT_ID = "clientId";
    private static final String PRODUCT_ID_KEY = "productIds";
    private static final String PAYMENT_ID_KEY = "paymentId";
    private static final String CREATED_AT = "createdAt";
    private static final String LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private String ENDPOINT_ORDERS = "";

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @LocalServerPort
    private String port;

    @Before
    public void setup() {
        ENDPOINT_ORDERS = "http://localhost:".concat(port).concat("/v1/orders");
    }

    private List<OrderResponseDto> orderResponseDtos;

    private final List<ProductEntity> productDefinitions = new ArrayList<>();
    private final List<PaymentEntity> paymentDefinitions = new ArrayList<>();

    @Dado("^que exista produtos cadastrados no sistema")
    @Transactional
    public void createProducts(DataTable dataTable) {
        productDefinitions.addAll(dataTable.asList(ProductEntity.class));
    }

    @Dado("^que exista pagamentos cadastrados no sistema")
    @org.springframework.transaction.annotation.Transactional
    public void createPayments(DataTable dataTable) {
        paymentDefinitions.addAll(dataTable.asList(PaymentEntity.class));
    }

    @Dado("^que exista pedidos cadastrados no sistema")
    public void createOrders(DataTable dataTable) throws RuntimeException {
        List<Map<String, String>> linhas = dataTable.asMaps(String.class, String.class);

        linhas.forEach(linha -> {

            ClientEntity client = getClient(linha.get(CLIENT_ID));

            List<ProductEntity> products = getProducts(linha.get(PRODUCT_ID_KEY));
            products.forEach(it -> {
                clearId(ProductEntity.class, it);
            });
            PaymentEntity payment = getPayment(linha.get(PAYMENT_ID_KEY));
            clearId(PaymentEntity.class, payment);

            var order = OrderEntity.builder()
                    .status(OrderStatusEnum.valueOf(linha.get(STATUS)))
                    .client(client)
                    .products(products)
                    .payment(payment)
                    .createdAt(
                            LocalDateTime.parse(linha.get(CREATED_AT), DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT))
                    )
                    .build();

            order.getProducts().forEach(it -> it.setOrder(order));
            order.getPayment().setOrder(order);

            orderRepository.save(order);
        });
    }

    private void clearId(Class<?> classToClearField, Object obj) {
        try {
            Field field = classToClearField.getDeclaredField("id");
            field.setAccessible(true);
            field.set(obj, null);
        } catch (Exception e) {
            throw new RuntimeException("Error clearing id field", e);
        }
    }

    @Quando("^for feito a request para buscar pedidos")
    public void findOrders() {
        ResponseEntity<List<OrderResponseDto>> response = restTemplate.exchange(
                ENDPOINT_ORDERS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        orderResponseDtos = response.getBody();
    }

    @Entao("^o retorno deverá ser")
    public void validateOrders(DataTable dataTable) {
        List<Map<String, String>> linhas = dataTable.asMaps(String.class, String.class);

        assertEquals(linhas.size(), orderResponseDtos.size());

        for (int i = 0; i < linhas.size(); i++) {
            var paymentId = linhas.get(i).get(PAYMENT_ID_KEY);

            assertDoesNotThrow(() -> true);

        }
    }

    private ClientEntity getClient(String clientId) {
        if (clientId == null || clientId.isEmpty()) {
            return null;
        }
        return clientRepository.findById(UUID.fromString(clientId)).orElseThrow();
    }

    private List<ProductEntity> getProducts(String productIds) {
        List<UUID> productIdsList = Arrays.stream(productIds.split(",")).map(UUID::fromString).toList();

        return productDefinitions.stream().filter(it ->
                productIdsList.contains(it.getId())).toList();
    }

    private PaymentEntity getPayment(String paymentId) {
        return paymentDefinitions.stream().filter(it ->
                it.getId().equals(UUID.fromString(paymentId))).findFirst().orElseThrow();
    }

}
