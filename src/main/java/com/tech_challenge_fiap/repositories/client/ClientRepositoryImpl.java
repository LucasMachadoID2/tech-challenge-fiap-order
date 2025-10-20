package com.tech_challenge_fiap.repositories.client;

import com.tech_challenge_fiap.dtos.external.ClientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    @Override
    public ClientDTO findById(String id) {
        try {
            return new ClientDTO();
        } catch (Exception e) {
            log.error("Error finding client by id: {}", id, e);
            throw new RuntimeException("Failed to find client by id", e);
        }
    }
}
