package com.tech_challenge_fiap.api;

import com.tech_challenge_fiap.controller.payment.PaymentController;
import com.tech_challenge_fiap.dtos.PaymentRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payments")
@AllArgsConstructor
public class PaymentApi {

    private final PaymentController paymentController;

    @PatchMapping
    public ResponseEntity<?> create(@RequestBody PaymentRequestDto paymentRequestDto) {
        paymentController.updateStatus(paymentRequestDto);
        return ResponseEntity.ok().build();
    }
}
