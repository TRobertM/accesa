package com.demo.service.interfaces;

import com.demo.dto.BestDiscountDTO;
import org.springframework.data.domain.Page;

public interface IDiscountService {
    Page<BestDiscountDTO> getBestDiscounts(int size, int page);
}
