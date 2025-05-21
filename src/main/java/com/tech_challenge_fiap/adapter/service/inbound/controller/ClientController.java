package com.tech_challenge_fiap.adapter.service.inbound.controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.beans.PropertyEditorSupport;
import com.tech_challenge_fiap.adapter.service.inbound.dto.ClientRequestDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.ClientResponseDto;
import com.tech_challenge_fiap.core.domain.client.ClientUseCase;
import com.tech_challenge_fiap.util.converter.ClientConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.tech_challenge_fiap.util.converter.ClientConverter.*;

@RestController
@RequestMapping("/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientUseCase clientUseCase;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, "cpf", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null) {
                    setValue(text.replaceAll("[^0-9]", ""));
                } else {
                    setValue(null);
                }
            }
        });
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody @jakarta.validation.Valid ClientRequestDto request) {
        var created = clientUseCase.create(toDomain(request));
        return ResponseEntity.ok(toResponse(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable String id, @RequestBody ClientRequestDto request) {
        var updated = clientUseCase.update(id, toDomain(request));
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        clientUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> list() {
        var clients = clientUseCase.findAll().stream()
                .map(ClientConverter::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clients);
    }
}
