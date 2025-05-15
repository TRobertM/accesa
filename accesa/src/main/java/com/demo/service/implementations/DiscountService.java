package com.demo.service.implementations;

import com.demo.dto.BestDiscountDTO;
import com.demo.mapper.DiscountMapper;
import com.demo.model.ProductDiscount;
import com.demo.model.ProductPrice;
import com.demo.repository.ProductDiscountRepository;
import com.demo.repository.ProductPriceRepository;
import com.demo.service.interfaces.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
public class DiscountService implements IDiscountService {

    private final ProductDiscountRepository productDiscountRepository;
    private final ProductPriceRepository productPriceRepository;
    private final DiscountMapper discountMapper;

    @Autowired
    public DiscountService(ProductDiscountRepository productDiscountRepository, ProductPriceRepository productPriceRepository, DiscountMapper discountMapper) {
        this.productDiscountRepository = productDiscountRepository;
        this.productPriceRepository = productPriceRepository;
        this.discountMapper = discountMapper;
    }

    @Override
    public Page<BestDiscountDTO> getBestDiscounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDate date = LocalDate.of(2025, Month.MAY, 10); // Specific date so there exists available discounts for the demo
        Page<ProductDiscount> bestDiscounts = productDiscountRepository.findBestActiveDiscounts(date, pageable);
        return bestDiscounts.map(
                discount -> {
                    ProductPrice originalPrice = productPriceRepository.findTopByProductIdAndStoreIdOrderByDateDesc(
                            discount.getProduct().getId(),
                            discount.getStore().getId()
                    );
                    return discountMapper.discountToBestDiscountDTO(discount, originalPrice.getPrice());
                }
        );
    }
}
