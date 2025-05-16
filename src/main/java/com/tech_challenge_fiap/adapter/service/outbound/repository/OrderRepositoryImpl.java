package com.tech_challenge_fiap.adapter.service.outbound.repository;

import com.tech_challenge_fiap.adapter.service.outbound.entity.OrderEntity;
import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.order.OrderRepository;
import com.tech_challenge_fiap.util.exception.OrderNotFoundExpection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.tech_challenge_fiap.util.converter.OrderConverter.entityToOrder;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final MongoOrderRepository mongoOrderRepository;

    @Override
    public Order save(OrderEntity order) {
        var savedOrder = mongoOrderRepository.save(order);
        return entityToOrder(savedOrder);
    }

    @Override
    public Order getOrderById(String id) {
        try {
            var order = mongoOrderRepository.findById(id).orElseThrow();
            return entityToOrder(order);
        } catch (Exception e) {
            throw new OrderNotFoundExpection(id);
        }
    }
}
