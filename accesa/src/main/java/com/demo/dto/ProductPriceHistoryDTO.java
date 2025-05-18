package com.demo.dto;

import java.util.List;

public record ProductPriceHistoryDTO(
        ProductDTO product,
        List<PricePointDTO> priceHistory
) {}
