package com.tech_challenge_fiap.usecases.client;

import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.utils.exceptions.ClientNotFoundException;
import com.tech_challenge_fiap.utils.exceptions.CpfAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ClientUseCaseImpl implements ClientUseCase {

    private final ClientGateway clientGateway;

    @Override
    public ClientEntity create(ClientEntity clientEntity) {
        ClientEntity client = clientGateway.findByCpf(clientEntity.getCpf());

        if (nonNull(client)) {
            throw new CpfAlreadyExistsException(clientEntity.getCpf());
        }

        return clientGateway.save(clientEntity);
    }

    @Override
    public ClientEntity update(String id, ClientEntity clientEntity) {
        if (isNull(clientGateway.findById(id))) {
            throw new ClientNotFoundException("Cliente não encontrado para o id: " + id);
        }

        clientEntity.setId(id);
        return clientGateway.save(clientEntity);
    }

    @Override
    public void delete(String id) {
        if (isNull(clientGateway.findById(id))) {
            throw new ClientNotFoundException("Cliente não encontrado para o id: " + id);
        }

        clientGateway.deleteById(id);
    }

    @Override
    public ClientEntity findById(String id) {
        ClientEntity client = clientGateway.findById(id);

        if (isNull(client)) {
            throw new ClientNotFoundException("Cliente não encontrado para o id: " + id);
        }

        return clientGateway.findById(id);
    }

    @Override
    public List<ClientEntity> findAll() {
        return clientGateway.findAll();
    }
}
