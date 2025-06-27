package com.tech_challenge_fiap.api;

import com.tech_challenge_fiap.controller.client.ClientController;
import com.tech_challenge_fiap.dtos.ClientRequestDto;
import com.tech_challenge_fiap.dtos.ClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientApi {

    private final ClientController clientController;

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody ClientRequestDto request) {
        var created = clientController.create(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable String id, @RequestBody ClientRequestDto client) {
        var updated = clientController.update(id, client);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        clientController.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> list() {
        var clients = clientController.findAll();
        return ResponseEntity.ok(clients);
    }
}
