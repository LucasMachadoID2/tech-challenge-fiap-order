package com.tech_challenge_fiap.repositories.order;

import com.tech_challenge_fiap.data.models.OrderDataModel;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoOrderRepository extends MongoRepository<OrderDataModel, String> {

    @Aggregation(pipeline = {
            "{ $match: { status: { $nin: ['FINALIZED', 'CREATED'] } } }",
            "{ $addFields: { orderStatus: { $switch: { branches: [ " +
                    "{ case: { $eq: ['$status', 'READY'] }, then: 1 }, " +
                    "{ case: { $eq: ['$status', 'IN_PREPARATION'] }, then: 2 }, " +
                    "{ case: { $eq: ['$status', 'RECEIVED'] }, then: 3 } " +
                    "], default: 99 } } } }",
            "{ $sort: { orderStatus: 1, dateCreated: 1 } }"
    })
    List<OrderDataModel> findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
}
