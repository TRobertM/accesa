package com.demo.service.implementations;

import com.demo.dto.PricedProductDTO;
import com.demo.mapper.ProductMapper;
import com.demo.model.ProductDiscount;
import com.demo.model.ProductPrice;
import com.demo.repository.ProductDiscountRepository;
import com.demo.repository.ProductPriceRepository;
import com.demo.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductDiscountRepository productDiscountRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductPriceRepository productPriceRepository, ProductDiscountRepository productDiscountRepository, ProductMapper productMapper) {
        this.productPriceRepository = productPriceRepository;
        this.productDiscountRepository = productDiscountRepository;
        this.productMapper = productMapper;
    }

    public List<PricedProductDTO> findProductById(String id) {
        List<ProductPrice> prices = productPriceRepository.findAllByProductIdAndActiveTrue(id);
        List<ProductDiscount> discounts = productDiscountRepository.findByProductIdAndActiveTrue(id);
        List<PricedProductDTO> pricedProductDTOS = new ArrayList<>();
        for (ProductPrice price : prices) {
            Optional<Integer> discount = discounts.stream()
                    .filter(d -> d.getStore().equals(price.getStore()))
                    .map(ProductDiscount::getDiscount)
                    .findFirst();
            pricedProductDTOS.add(
                    productMapper.productToPricedProductDTO(price, discount.orElse(0))
            );
        }
        return pricedProductDTOS;
    };
}
