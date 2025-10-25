package com.tech_challenge_fiap.repositories.client;

import com.tech_challenge_fiap.dtos.external.ClientDTO;
import com.tech_challenge_fiap.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    ClientDTO findById(String id);
}
