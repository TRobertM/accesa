package com.demo.mapper;

import com.demo.dto.BasketItemDTO;
import com.demo.model.BasketItem;
import org.springframework.stereotype.Component;

@Component
public class BasketItemMapper {

    private final ProductMapper productMapper;

    public BasketItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public BasketItemDTO itemToDTO(BasketItem item) {
        return new BasketItemDTO(
                productMapper.productToDTO(item.getProduct()),
                item.getQuantity()
        );
    }
}
