package com.demo.service.interfaces;

import com.demo.dto.DiscountDTO;
import org.springframework.data.domain.Page;

public interface IDiscountService {
    Page<DiscountDTO> getBestDiscounts(int size, int page);
    Page<DiscountDTO> getNewestDiscounts(int size, int page);
}
