package com.tech_challenge_fiap.scripts;

import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.PostConstruct;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class PaymentStatusMigration {

    private final MongoTemplate mongoTemplate;

    public PaymentStatusMigration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void migratePaymentStatusValues() {
        Map<String, String> statusMappings = Map.of(
                "WAITING_PAYMENT", "CRIADO",
                "PAID", "PAGO",
                "CANCELED", "RECUSADO"
        );

        statusMappings.forEach((oldStatus, newStatus) -> {
            UpdateResult result = mongoTemplate.updateMulti(
                    Query.query(Criteria.where("payment.status").is(oldStatus)),
                    Update.update("payment.status", newStatus),
                    "order"
            );
            System.out.printf("Migrated %s to %s: %d documents%n",
                    oldStatus, newStatus, result.getModifiedCount());
        });
    }
}