package com.tech_challenge_fiap.core.domain.order;

import com.tech_challenge_fiap.entrypoint.dto.OrderRequestDto;

public interface OrderUseCase {

    void createOrder(OrderRequestDto orderRequestDTO);
}
