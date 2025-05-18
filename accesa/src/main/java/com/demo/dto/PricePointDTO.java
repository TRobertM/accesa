package com.demo.dto;

import java.time.LocalDate;

public record PricePointDTO(
        LocalDate date,
        double price,
        int discountPercentage,
        double finalPrice,
        StoreDTO store
) {
    public PricePointDTO(LocalDate date, double price, int discountPercentage, StoreDTO store) {
        this(date, price, discountPercentage, price * (1 - discountPercentage / 100.0), store);
    }
}
