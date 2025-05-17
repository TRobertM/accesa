package com.demo.dto;

public record ShoppingListItemDTO(
        Long id,
        ProductDTO product,
        Integer quantity,
        Double price,
        Integer discount,
        Double totalPrice
) {}
