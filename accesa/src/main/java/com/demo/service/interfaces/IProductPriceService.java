package com.demo.service.interfaces;

import com.demo.dto.PriceHistoryRequestDTO;
import com.demo.dto.ProductPriceHistoryDTO;

import java.util.List;

public interface IProductPriceService {
    List<ProductPriceHistoryDTO> getPriceHistory(PriceHistoryRequestDTO priceHistoryRequestDTO);
}
