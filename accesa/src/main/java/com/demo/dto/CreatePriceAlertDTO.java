package com.demo.dto;

public record CreatePriceAlertDTO(
        String productId,
        Double wantedPrice
) {}
