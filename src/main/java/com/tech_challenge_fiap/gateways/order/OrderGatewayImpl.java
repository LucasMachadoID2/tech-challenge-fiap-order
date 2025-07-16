package com.tech_challenge_fiap.gateways.order;

import com.tech_challenge_fiap.adapters.OrderAdapter;
import com.tech_challenge_fiap.data.models.OrderDataModel;
import com.tech_challenge_fiap.entities.order.OrderEntity;
import com.tech_challenge_fiap.repositories.order.MongoOrderRepository;
import com.tech_challenge_fiap.utils.exceptions.OrderNotFoundException;
import com.tech_challenge_fiap.utils.exceptions.OrdersNotFoundExpection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.tech_challenge_fiap.adapters.OrderAdapter.toDataModel;
import static com.tech_challenge_fiap.adapters.OrderAdapter.toEntity;

@RequiredArgsConstructor
@Component
public class OrderGatewayImpl implements OrderGateway {

    private final MongoOrderRepository mongoOrderRepository;

    @Override
    public OrderEntity save(OrderEntity order) {
        var savedOrder = mongoOrderRepository.save(toDataModel(order));
        return toEntity(savedOrder);
    }

    @Override
    public OrderEntity getOrderById(String id) {
        var order = mongoOrderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return toEntity(order);
    }

    @Override
    public List<OrderEntity> findAllOrderedByStatusAndCreatedAtIgnoringFinalized() {
        List<OrderDataModel> orderEntities = mongoOrderRepository.findAllOrderedByStatusAndCreatedAtIgnoringFinalized();

        if (orderEntities.isEmpty()) {
            throw new OrdersNotFoundExpection();
        }

        return orderEntities.stream().map(OrderAdapter::toEntity).collect(Collectors.toList());
    }
}
