package com.demo.dto;

import java.time.LocalDate;
import java.util.Optional;

public record PriceHistoryRequestDTO(
        Optional<Integer> daysBetweenPoints,
        Optional<String> productId,
        Optional<String> storeId,
        Optional<String> category,
        Optional<String> brand,
        LocalDate startDate
) {}
