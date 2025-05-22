package com.demo.service.interfaces;

import com.demo.dto.BasketDTO;
import com.demo.dto.BasketItemCreationDTO;
import com.demo.dto.ProductDTO;

public interface IBasketService {
    BasketDTO getBasket();
    ProductDTO addToBasket(BasketItemCreationDTO basketItemCreationDTO);
}
