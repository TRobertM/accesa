package com.demo.service;

import com.demo.exception.InvalidPackageUnitException;
import com.demo.model.Product;
import com.demo.model.ProductDiscount;
import com.demo.model.ProductPrice;
import com.demo.model.Store;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

@Service
public class PriceService {
    public record BestPriceResult(Store store, double price, int discount) {}

    private static final Map<String, Double> UNIT_CONVERSION = Map.ofEntries(
            entry("g", 0.001),
            entry("kg", 1.0),
            entry("mg", 0.000001),
            entry("l", 1.0),
            entry("ml", 0.001),
            entry("buc", 1.0),
            entry("role", 1.0)
    );

    private static final Map<String, String> UNIT_DISPLAY = Map.ofEntries(
            entry("g", "kg"),
            entry("kg", "kg"),
            entry("mg", "kg"),
            entry("l", "l"),
            entry("ml", "l"),
            entry("buc", "buc"),
            entry("role", "rola")
    );

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

    public Pair<Double, String> calculatePricePerUnit(Double price, Double quantity, String unit) {
        Double conversionFactor = UNIT_CONVERSION.get(unit.toLowerCase());
        if (conversionFactor == null) {
            throw new InvalidPackageUnitException();
        }
        return Pair.of(price / (conversionFactor * quantity), UNIT_DISPLAY.get(unit.toLowerCase()));
    }
}
