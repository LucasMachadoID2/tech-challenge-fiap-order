package com.tech_challenge_fiap.gateways.order;

import com.tech_challenge_fiap.repositories.MongoOrderRepository;
import com.tech_challenge_fiap.data.models.OrderDataModel;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.adapters.OrderAdapter;
import com.tech_challenge_fiap.util.exception.OrderNotFoundException;
import com.tech_challenge_fiap.util.exception.OrdersNotFoundExpection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.tech_challenge_fiap.adapters.OrderAdapter.entityToOrder;

@RequiredArgsConstructor
@Component
public class OrderGatewayImpl implements OrderGateway {

    private final MongoOrderRepository mongoOrderRepository;

    @Override
    public OrderEntity save(OrderDataModel order) {
        var savedOrder = mongoOrderRepository.save(order);
        return entityToOrder(savedOrder);
    }

    @Override
    public OrderEntity getOrderById(String id) {
        var order = mongoOrderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return entityToOrder(order);
    }

    @Override
    public List<OrderEntity> findAll() {
        List<OrderDataModel> orderEntities = mongoOrderRepository.findAll();
        if (orderEntities.isEmpty()) {
            throw new OrdersNotFoundExpection();
        }

        return orderEntities.stream().map(OrderAdapter::entityToOrder).collect(Collectors.toList());
    }
}
