package com.demo.dto;

import java.time.LocalDate;

public record PricePointDTO(
        LocalDate date,
        Double price,
        Integer discountPercentage,
        Double finalPrice,
        StoreDTO store
) {
    public PricePointDTO(LocalDate date, Double price, Integer discountPercentage, StoreDTO store) {
        this(date, price, discountPercentage, price * (1 - discountPercentage / 100.0), store);
    }
}
