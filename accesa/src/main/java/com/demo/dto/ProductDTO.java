package com.demo.dto;

public record ProductDTO(
        String id,
        String name,
        String category,
        String brand,
        Double packageQuantity,
        String packageUnit
) {}
