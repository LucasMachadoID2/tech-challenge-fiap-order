package com.tech_challenge_fiap.adapter.service.inbound.controller;

import com.tech_challenge_fiap.core.domain.order.OrderUseCase;
import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
@AllArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping("create")
    public ResponseEntity<?> create(OrderRequestDto orderRequestDTO) {
        try {
            orderUseCase.createOrder(orderRequestDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
