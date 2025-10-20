package com.tech_challenge_fiap.repositories.order;

import com.tech_challenge_fiap.data.models.OrderDataModel;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public OrderDataModel save(OrderDataModel order) {
        try {
            log.info("Saving order with ID: {}", order.getId());
            return order;
        } catch (Exception e) {
            log.error("Error saving order with ID: {}", order.getId(), e);
            throw new RuntimeException("Failed to save order", e);
        }
    }

    @Override
    public Optional<OrderDataModel> findById(String id) {
        try {
            log.debug("Finding order by ID: {}", id);
            return Optional.of(null);
        } catch (Exception e) {
            log.error("Error finding order by ID: {}", id, e);
            throw new RuntimeException("Failed to find order by ID", e);
        }
    }

    @Override
    public List<OrderDataModel> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated() {
        try {
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Error finding all orders", e);
            throw new RuntimeException("Failed to find all orders", e);
        }
    }

    @Override
    public List<OrderDataModel> findByStatus(OrderEntityStatusEnum status) {
        try {
           return Collections.emptyList();
        } catch (Exception e) {
            log.error("Error finding orders by status: {}", status, e);
            throw new RuntimeException("Failed to find orders by status", e);
        }
    }

    // Método adicional para buscar pedidos por CPF usando o GSI ByCpf
    @Override
    public List<OrderDataModel> findByClientId(String clientId) {
        try {
            log.debug("Finding orders by clientId: {}", clientId);

            return Collections.emptyList();
        } catch (Exception e) {
            log.error("Error finding orders by clientId: {}", clientId, e);
            throw new RuntimeException("Failed to find orders by clientId", e);
        }
    }
}