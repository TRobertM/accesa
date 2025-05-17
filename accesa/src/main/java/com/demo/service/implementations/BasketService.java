package com.demo.service.implementations;

import com.demo.dto.BasketDTO;
import com.demo.mapper.BasketMapper;
import com.demo.model.Basket;
import com.demo.repository.BasketRepository;
import com.demo.security.SecurityUtil;
import com.demo.service.interfaces.IBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService implements IBasketService {

    private final BasketRepository basketRepository;
    private final SecurityUtil securityUtil;
    private final BasketMapper basketMapper;

    @Autowired
    public BasketService(BasketRepository basketRepository, SecurityUtil securityUtil, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.securityUtil = securityUtil;
        this.basketMapper = basketMapper;
    }

    public BasketDTO getBasket() {
        String userId = securityUtil.getUserId();
        Basket basket = basketRepository.findByUserId(userId);
        return basketMapper.basketToDTO(basket);
    }
}
