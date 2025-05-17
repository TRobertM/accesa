package com.demo.mapper;

import com.demo.dto.BasketDTO;
import com.demo.model.Basket;
import org.springframework.stereotype.Component;

@Component
public class BasketMapper {

    private final BasketItemMapper basketItemMapper;

    public BasketMapper(BasketItemMapper basketItemMapper) {
        this.basketItemMapper = basketItemMapper;
    }

    public BasketDTO basketToDTO(Basket basket) {
        return new BasketDTO(
                basket.getId(),
                basket.getBasketItems().stream()
                        .map(basketItemMapper::itemToDTO)
                        .toList()
        );
    }
}
