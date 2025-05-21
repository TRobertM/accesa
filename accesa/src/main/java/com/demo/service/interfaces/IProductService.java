package com.demo.service.interfaces;

import com.demo.dto.PricedProductDTO;

import java.util.List;

public interface IProductService {
    List<PricedProductDTO> findProductById(String id);
}
