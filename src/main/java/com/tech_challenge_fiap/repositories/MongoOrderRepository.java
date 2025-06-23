package com.tech_challenge_fiap.repositories;

import com.tech_challenge_fiap.data.models.OrderDataModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOrderRepository extends MongoRepository<OrderDataModel, String> {
}
