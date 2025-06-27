package com.tech_challenge_fiap.repositories.client;

import com.tech_challenge_fiap.data.models.ClientDataModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoClientRepository extends MongoRepository<ClientDataModel, String> {

    ClientDataModel findByCpf(String cpf);
}

