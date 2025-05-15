package com.demo.dto;

public record ProductDTO(
        String id,
        String name,
        String category,
        String brand,
        Double package_quantity,
        String package_unit
) {}
