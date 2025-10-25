package com.tech_challenge_fiap.dtos.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    
    private String status;
    
    private ClientResponseDto client;
    
    private List<ProductResponseDto> products;

    private PaymentResponseDto payment;

    private LocalDateTime createdAt;
}
