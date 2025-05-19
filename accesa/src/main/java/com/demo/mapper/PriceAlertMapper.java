package com.demo.mapper;

import com.demo.dto.PriceAlertDTO;
import com.demo.model.PriceAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceAlertMapper {

    private final ProductMapper productMapper;
    private final UserMapper userMapper;

    @Autowired
    public PriceAlertMapper(ProductMapper productMapper, UserMapper userMapper) {
        this.productMapper = productMapper;
        this.userMapper = userMapper;
    }

    public PriceAlertDTO priceAlertToDTO(PriceAlert priceAlert) {
        return new PriceAlertDTO(
                priceAlert.getId(),
                productMapper.productToDTO(priceAlert.getProduct()),
                userMapper.toUserDTO(priceAlert.getUser()),
                priceAlert.getWanted_price()
        );
    }
}
