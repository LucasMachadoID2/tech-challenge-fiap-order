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
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Dado("^que exista pedidos cadastrados no sistema")
    public void createOrders(DataTable dataTable) {
        List<Map<String, String>> linhas = dataTable.asMaps(String.class, String.class);

        linhas.forEach(linha -> {

            ClientEntity client = getClient(linha.get(CLIENT_ID));

            List<ProductEntity> products = getProducts(linha.get(PRODUCT_ID_KEY));
            PaymentEntity payment = getPayment(UUID.fromString(linha.get(PAYMENT_ID_KEY)));

            var order = OrderEntity.builder()
                    .id(UUID.fromString(linha.get(ID_KEY)))
                    .status(OrderStatusEnum.valueOf(linha.get(STATUS)))
                    .client(client)
                    .products(products)
                    .payment(payment)
                    .createdAt(
                            LocalDateTime.parse(linha.get(CREATED_AT), DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMAT))
                    )
                    .build();

            orderRepository.save(order);
        });
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

        for (int i = 0; i < linhas.size(); i++) {
            assertEquals(UUID.fromString(linhas.get(i).get(ID_KEY)), orderResponseDtos.get(i).getId());
        }
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
