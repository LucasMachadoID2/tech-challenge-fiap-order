package com.tech_challenge_fiap.repositories.order;

import com.tech_challenge_fiap.data.models.OrderDataModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final MongoOrderRepository mongoOrderRepository;

    @Override
    public OrderDataModel save(OrderDataModel order) {
        return mongoOrderRepository.save(order);
    }

    @Override
    public Optional<OrderDataModel> findById(String id) {
        return mongoOrderRepository.findById(id);
    }

    @Override
    public List<OrderDataModel> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated() {
        return mongoOrderRepository.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
    }
}
