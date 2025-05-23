package com.tech_challenge_fiap.adapter.service.inbound.controller;

import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderRequestDto;
import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderUpdateStatusRequestDto;
import com.tech_challenge_fiap.core.domain.order.Order;
import com.tech_challenge_fiap.core.domain.order.OrderUseCase;
import com.tech_challenge_fiap.util.converter.OrderConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tech_challenge_fiap.util.converter.OrderConverter.toResponse;

@RestController
@RequestMapping("/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody OrderRequestDto orderRequestDTO) {
        try {
            var savedOrder = orderUseCase.createOrder(orderRequestDTO);
            return ResponseEntity.ok(toResponse(savedOrder));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> list() {
        try {
            List<Order> orders = orderUseCase.findAll();
            return ResponseEntity.ok(orders.stream().map(OrderConverter::toResponse).toList());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<?> updateStatus(@RequestBody OrderUpdateStatusRequestDto orderUpdateStatusRequestDto) {
        try {
            var updatedOrder = orderUseCase.updateStatus(orderUpdateStatusRequestDto);
            return ResponseEntity.ok(toResponse(updatedOrder));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
