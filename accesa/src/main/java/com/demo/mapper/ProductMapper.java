package com.demo.mapper;

import com.demo.dto.PricedProductDTO;
import com.demo.dto.ProductDTO;
import com.demo.model.Product;
import com.demo.model.ProductPrice;
import com.demo.service.PriceService;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final StoreMapper storeMapper;
    private final PriceService priceService;

    public ProductMapper(StoreMapper storeMapper, PriceService priceService) {
        this.storeMapper = storeMapper;
        this.priceService = priceService;
    }

    public ProductDTO productToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getBrand(),
                product.getPackage_quantity(),
                product.getPackage_unit()
        );
    }

    public PricedProductDTO productToPricedProductDTO(ProductPrice product, Integer discount) {
        Double discountedPrice = product.getPrice() - (product.getPrice() * discount / 100);
        Pair<Double, String> pricePerUnitAndUnit = priceService.calculatePricePerUnit(
                discountedPrice,
                product.getProduct().getPackage_quantity(),
                product.getProduct().getPackage_unit()
        );
        return new PricedProductDTO(
                storeMapper.storeToDTO(product.getStore()),
                productToDTO(product.getProduct()),
                discountedPrice,
                discount,
                pricePerUnitAndUnit.getFirst(),
                pricePerUnitAndUnit.getSecond()
        );
    }
}
