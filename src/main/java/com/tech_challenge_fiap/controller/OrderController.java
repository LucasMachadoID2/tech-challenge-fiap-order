package com.tech_challenge_fiap.controller;

import com.tech_challenge_fiap.domains.order.OrderStatusEnum;
import com.tech_challenge_fiap.domains.payment.PaymentStatusEnum;
import com.tech_challenge_fiap.dtos.internal.OrderRequestDto;
import com.tech_challenge_fiap.dtos.internal.OrderResponseDto;
import com.tech_challenge_fiap.services.order.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Order", description = "Operations related to order management")
@RestController
@RequestMapping("/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderRequestDto orderRequestDTO) {
        var savedOrder = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> list() {
        List<OrderResponseDto> orderEntities = orderService.findAllOrderedByStatusAndCreatedAtIgnoringFinalizedAndCreated();
        return ResponseEntity.ok(orderEntities);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateStatus(@PathVariable String orderId,
                                                         @RequestParam OrderStatusEnum status) {
        var updatedOrder = orderService.updateStatus(UUID.fromString(orderId), status);
        return ResponseEntity.ok(updatedOrder);
    }

    @PatchMapping("/update-payment-status/{paymentId}")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable String paymentId,
                                                                @RequestParam PaymentStatusEnum status) {
        orderService.updatePaymentStatus(UUID.fromString(paymentId), status);
        return ResponseEntity.ok().build();
    }
}
