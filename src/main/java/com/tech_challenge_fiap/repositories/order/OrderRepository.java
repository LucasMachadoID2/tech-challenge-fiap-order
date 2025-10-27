package com.tech_challenge_fiap.repositories.order;

import com.tech_challenge_fiap.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("SELECT o FROM OrderEntity o " +
            "WHERE o.status NOT IN (FINALIZED, CREATED) " +
            "ORDER BY o.status ASC, o.createdAt ASC")
    List<OrderEntity> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
}
