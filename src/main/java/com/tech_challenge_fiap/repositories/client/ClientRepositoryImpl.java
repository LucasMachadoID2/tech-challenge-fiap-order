package com.tech_challenge_fiap.repositories.client;

import com.tech_challenge_fiap.data.models.ClientDataModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final MongoClientRepository mongoRepository;

    @Override
    public ClientDataModel save(ClientDataModel client) {
        return mongoRepository.save(client);
    }

    @Override
    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public Optional<ClientDataModel> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public List<ClientDataModel> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public ClientDataModel findByCpf(String cpf) {
        return mongoRepository.findByCpf(cpf);
    }
}
