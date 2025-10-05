package com.tech_challenge_fiap.repositories.client;

import com.tech_challenge_fiap.data.models.ClientDataModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final String tableName = "tech-challenge-users";

    private DynamoDbTable<ClientDataModel> getClientTable() {
        return dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(ClientDataModel.class));
    }

    @Override
    public ClientDataModel save(ClientDataModel client) {
        try {
            if (client.getId() == null || client.getId().isBlank()) {
                client.setId(UUID.randomUUID().toString());
            }
            log.info("Saving client with id: {}", client.getId());
            getClientTable().putItem(client);
            return client;
        } catch (Exception e) {
            log.error("Error saving client with id: {}", client.getId(), e);
            throw new RuntimeException("Failed to save client", e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            log.info("Deleting client with id: {}", id);
            Key key = Key.builder().partitionValue(id).build();
            getClientTable().deleteItem(key);
        } catch (Exception e) {
            log.error("Error deleting client with id: {}", id, e);
            throw new RuntimeException("Failed to delete client by id", e);
        }
    }

    @Override
    public Optional<ClientDataModel> findById(String id) {
        try {
            log.debug("Finding client by id: {}", id);
            Key key = Key.builder().partitionValue(id).build();
            ClientDataModel client = getClientTable().getItem(r -> r.key(key));
            return Optional.ofNullable(client);
        } catch (Exception e) {
            log.error("Error finding client by id: {}", id, e);
            throw new RuntimeException("Failed to find client by id", e);
        }
    }

    @Override
    public List<ClientDataModel> findAll() {
        try {
            log.debug("Finding all clients");
            return getClientTable()
                    .scan()
                    .items()
                    .stream()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error finding all clients", e);
            throw new RuntimeException("Failed to find all clients", e);
        }
    }

    @Override
    public ClientDataModel findByCpf(String cpf) {
        try {
            log.debug("Finding client by cpf: {}", cpf);
            DynamoDbIndex<ClientDataModel> cpfIndex = getClientTable().index("CpfIndex");
            var maybe = cpfIndex.query(r -> r.queryConditional(QueryConditional.keyEqualTo(
                            Key.builder().partitionValue(cpf).build())))
                    .stream()
                    .flatMap(page -> page.items().stream())
                    .findFirst();
            return maybe.orElse(null);
        } catch (Exception e) {
            log.error("Error finding client by cpf: {}", cpf, e);
            throw new RuntimeException("Failed to find client by cpf", e);
        }
    }

    @Override
    public void deleteByCpf(String cpf) {
        try {
            log.info("Deleting client by cpf: {}", cpf);
            ClientDataModel client = findByCpf(cpf);
            if (client != null) {
                deleteById(client.getId());
            } else {
                log.warn("Client with cpf {} not found, nothing to delete", cpf);
            }
        } catch (Exception e) {
            log.error("Error deleting client by cpf: {}", cpf, e);
            throw new RuntimeException("Failed to delete client by cpf", e);
        }
    }

    // opcional
    public Optional<ClientDataModel> findByEmail(String email) {
        try {
            log.debug("Finding client by email: {}", email);
            DynamoDbIndex<ClientDataModel> emailIndex = getClientTable().index("EmailIndex");
            return emailIndex.query(r -> r.queryConditional(QueryConditional.keyEqualTo(
                            Key.builder().partitionValue(email).build())))
                    .stream()
                    .flatMap(page -> page.items().stream())
                    .findFirst();
        } catch (Exception e) {
            log.error("Error finding client by email: {}", email, e);
            throw new RuntimeException("Failed to find client by email", e);
        }
    }
}
