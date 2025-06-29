package com.tech_challenge_fiap.api;

import com.tech_challenge_fiap.controller.order.OrderController;
import com.tech_challenge_fiap.dtos.OrderRequestDto;
import com.tech_challenge_fiap.dtos.OrderResponseDto;
import com.tech_challenge_fiap.entities.order.OrderEntityStatusEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order", description = "Operations related to order management")
@RestController
@RequestMapping("/v1/orders")
@AllArgsConstructor
public class OrderApi {

    private final OrderController orderController;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderRequestDto orderRequestDTO) {
        var savedOrder = orderController.createOrder(orderRequestDTO);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> list() {
        List<OrderResponseDto> orderEntities = orderController.findAll();
        return ResponseEntity.ok(orderEntities);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable String orderId,
                                                         @RequestParam OrderEntityStatusEnum status) {
        var updatedOrder = orderController.updateStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }
}
