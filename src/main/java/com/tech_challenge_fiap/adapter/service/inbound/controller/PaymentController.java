package com.tech_challenge_fiap.adapter.service.inbound.controller;

import com.tech_challenge_fiap.adapter.service.inbound.dto.PaymentRequestDto;
import com.tech_challenge_fiap.core.domain.payment.PaymentUseCase;
import com.tech_challenge_fiap.util.exception.OrderNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    @PatchMapping
    public ResponseEntity<?> create(@RequestBody PaymentRequestDto paymentRequestDto) {
        try {
            var updatedOrder = paymentUseCase.updatePaymentStatus(paymentRequestDto);
            return ResponseEntity.ok(updatedOrder);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
