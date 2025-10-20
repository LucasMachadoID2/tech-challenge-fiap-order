package com.tech_challenge_fiap.repositories.client;

import com.tech_challenge_fiap.dtos.external.ClientDTO;

public interface ClientRepository {
    ClientDTO findById(String id);
}
