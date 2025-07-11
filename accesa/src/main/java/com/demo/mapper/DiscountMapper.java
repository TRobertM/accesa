package com.demo.mapper;

import com.demo.dto.DiscountDTO;
import com.demo.model.ProductDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {

    private final ProductMapper productMapper;
    private final StoreMapper storeMapper;

    @Autowired
    public DiscountMapper(ProductMapper productMapper, StoreMapper storeMapper) {
        this.productMapper = productMapper;
        this.storeMapper = storeMapper;
    }

    public DiscountDTO discountToDTO(ProductDiscount discount, Double originalPrice) {
       return new DiscountDTO(
               discount.getId(),
               productMapper.productToDTO(discount.getProduct()),
               storeMapper.storeToDTO(discount.getStore()),
               originalPrice,
               originalPrice * (1 - discount.getDiscount() / 100.0),
               discount.getDiscount(),
               discount.getStartDate(),
               discount.getEndDate()
       );
    }
}
