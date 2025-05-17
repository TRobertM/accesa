package com.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ShoppingListDTO(
        String id,
        String name,
        List<ShoppingListItemDTO> items,
        Double totalPrice,
        LocalDateTime createdAt
) {}
