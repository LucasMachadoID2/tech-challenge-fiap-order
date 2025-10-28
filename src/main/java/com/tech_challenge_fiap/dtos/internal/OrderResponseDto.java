package com.tech_challenge_fiap.dtos.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponseDto {
    private UUID id;
    
    private String status;
    
    private ClientResponseDto client;
    
    private List<ProductResponseDto> products;

    private PaymentResponseDto payment;

    private LocalDateTime createdAt;
}
