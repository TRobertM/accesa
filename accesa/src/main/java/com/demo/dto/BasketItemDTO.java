package com.demo.dto;

public record BasketItemDTO(
        ProductDTO product,
        Integer quantity
) {}
