package com.tech_challenge_fiap.usecases;

import com.tech_challenge_fiap.entities.client.ClientEntity;
import com.tech_challenge_fiap.gateways.client.ClientGateway;
import com.tech_challenge_fiap.utils.exceptions.ClientNotFoundException;
import com.tech_challenge_fiap.utils.exceptions.CpfAlreadyExistsException;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ClientUseCase {

    public static ClientEntity create(ClientEntity clientEntity, ClientGateway clientGateway) {
        ClientEntity client = clientGateway.findByCpf(clientEntity.getCpf());

        if (nonNull(client)) {
            throw new CpfAlreadyExistsException(clientEntity.getCpf());
        }

        return clientGateway.save(clientEntity);
    }


    public static ClientEntity update(String id, ClientEntity clientEntity, ClientGateway clientGateway) {
        if (isNull(clientGateway.findById(id))) {
            throw new ClientNotFoundException("Cliente não encontrado para o id: " + id);
        }

        clientEntity.setId(id);
        return clientGateway.save(clientEntity);
    }


    public static void delete(String id, ClientGateway clientGateway) {
        if (isNull(clientGateway.findById(id))) {
            throw new ClientNotFoundException("Cliente não encontrado para o id: " + id);
        }

        clientGateway.deleteById(id);
    }


    public static ClientEntity findById(String id, ClientGateway clientGateway) {
        ClientEntity client = clientGateway.findById(id);

        if (isNull(client)) {
            throw new ClientNotFoundException("Cliente não encontrado para o id: " + id);
        }

        return clientGateway.findById(id);
    }

    public static List<ClientEntity> findAll(ClientGateway clientGateway) {
        return clientGateway.findAll();
    }
}
