package com.demo.service;

import com.demo.model.Product;
import com.demo.model.ProductDiscount;
import com.demo.model.ProductPrice;
import com.demo.model.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    public record BestPriceResult(Store store, double price, int discount) {}

    public BestPriceResult findBestPrice(Product product, List<ProductPrice> prices) {
        double lowestPrice = Double.MAX_VALUE;
        Store bestStore = null;
        int bestDiscount = 0;

        for (ProductPrice price : prices) {
            Store store = price.getStore();
            double basePrice = price.getPrice();

            Optional<ProductDiscount> activeDiscount = store.getDiscounts().stream()
                    .filter(ProductDiscount::isActive)
                    .filter(d -> d.getProduct().equals(product))
                    .findFirst();

            double finalPrice = activeDiscount
                    .map(d -> basePrice * (100 - d.getDiscount()) / 100)
                    .orElse(basePrice);

            if (finalPrice < lowestPrice) {
                lowestPrice = finalPrice;
                bestStore = store;
                bestDiscount = activeDiscount.map(ProductDiscount::getDiscount).orElse(0);
            }
        }

        return new BestPriceResult(bestStore, lowestPrice, bestDiscount);
    }
}
