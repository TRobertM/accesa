package com.demo.dto;

import java.time.LocalDate;

public record DiscountDTO(
        String id,
        ProductDTO product,
        StoreDTO store,
        Double originalPrice,
        Double discountedPrice,
        Integer discountPercentage,
        LocalDate startDate,
        LocalDate endDate
) {}
