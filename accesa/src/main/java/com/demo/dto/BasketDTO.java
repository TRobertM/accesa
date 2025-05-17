package com.demo.dto;

import java.util.List;

public record BasketDTO(
        String id,
        List<BasketItemDTO> basketItems
) {}
