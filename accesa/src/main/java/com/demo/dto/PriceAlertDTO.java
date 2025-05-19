package com.demo.dto;

public record PriceAlertDTO(
        Long id,
        ProductDTO product,
        UserDTO user,
        Double wantedPrice
) {}
