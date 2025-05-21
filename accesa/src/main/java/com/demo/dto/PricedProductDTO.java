package com.demo.dto;

public record PricedProductDTO(
        StoreDTO store,
        ProductDTO product,
        Double price,
        Integer discount,
        Double pricePerUnit,
        String unit
) {}
