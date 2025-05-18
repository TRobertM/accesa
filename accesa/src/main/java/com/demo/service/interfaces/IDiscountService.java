package com.demo.service.interfaces;

import com.demo.dto.DiscountDTO;
import com.demo.dto.PriceHistoryRequestDTO;
import com.demo.model.ProductDiscount;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDiscountService {
    Page<DiscountDTO> getBestDiscounts(int size, int page);
    Page<DiscountDTO> getNewestDiscounts(int size, int page);
    List<ProductDiscount> getDiscountHistory(PriceHistoryRequestDTO priceHistoryRequestDTO);
}
