package com.tech_challenge_fiap.webhooks;

import com.tech_challenge_fiap.controller.payment.PaymentController;
import com.tech_challenge_fiap.dtos.PaymentRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Webhook")
@RestController
@RequestMapping("/v1/webhooks")
@RequiredArgsConstructor
public class MercadoPagoWebhook {

    private final PaymentController paymentController;

    @PostMapping
    public ResponseEntity<?> update(@RequestBody PaymentRequestDto paymentRequestDto) {
        paymentController.updateStatus(paymentRequestDto);
        return ResponseEntity.ok().build();
    }
}
