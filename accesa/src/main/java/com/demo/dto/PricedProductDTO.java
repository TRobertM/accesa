package com.demo.dto;

public record PricedProductDTO(
        StoreDTO storeDTO,
        ProductDTO product,
        Double price,
        Integer discount,
        Double pricePerUnit,
        String unit
) {}
