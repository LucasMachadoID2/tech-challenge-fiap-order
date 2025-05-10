package com.tech_challenge_fiap.core.domain.order;

import com.tech_challenge_fiap.adapter.service.inbound.dto.OrderRequestDto;

public interface OrderUseCase {

    Order createOrder(OrderRequestDto orderRequestDTO);
}
