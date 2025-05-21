package com.demo.service.implementations;

import com.demo.dto.PriceHistoryRequestDTO;
import com.demo.dto.PricePointDTO;
import com.demo.dto.ProductPriceHistoryDTO;
import com.demo.mapper.ProductMapper;
import com.demo.mapper.StoreMapper;
import com.demo.model.Product;
import com.demo.model.ProductDiscount;
import com.demo.model.ProductPrice;
import com.demo.model.Store;
import com.demo.repository.ProductDiscountRepository;
import com.demo.repository.ProductPriceRepository;
import com.demo.service.interfaces.IProductPriceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class ProductPriceService implements IProductPriceService {

    private final ProductPriceRepository productPriceRepository;
    private final ProductDiscountRepository productDiscountRepository;
    private final StoreMapper storeMapper;
    private final ProductMapper productMapper;

    public ProductPriceService(ProductPriceRepository productPriceRepository, ProductDiscountRepository productDiscountRepository, StoreMapper storeMapper, ProductMapper productMapper) {
        this.productPriceRepository = productPriceRepository;
        this.productDiscountRepository = productDiscountRepository;
        this.storeMapper = storeMapper;
        this.productMapper = productMapper;
    }

    public List<ProductPriceHistoryDTO> getPriceHistory(PriceHistoryRequestDTO request) {
        int daysBetween = request.daysBetweenPoints().orElse(7);
        LocalDate startDate;
        LocalDate endDate = LocalDate.of(2025, Month.MAY, 11);
        List<ProductPrice> prices = productPriceRepository.findPriceHistory(
                request.productId().orElse(null),
                request.storeId().orElse(null),
                request.category().orElse(null),
                request.brand().orElse(null)
        );
        List<ProductDiscount> discounts = productDiscountRepository.findPriceHistory(
                request.productId().orElse(null),
                request.storeId().orElse(null),
                request.category().orElse(null),
                request.brand().orElse(null)
        );
        List<Product> products = prices.stream().map(ProductPrice::getProduct).distinct().toList();
        List<ProductPriceHistoryDTO> result = new ArrayList<>();
        for (Product product : products) {
            List<PricePointDTO> allPricePoints = new ArrayList<>();
            List<Store> stores = prices.stream()
                    .filter(price -> price.getProduct().equals(product))
                    .map(ProductPrice::getStore)
                    .distinct()
                    .toList();
            for (Store store : stores) {
                startDate = request.startDate();
                List<ProductPrice> availablePrices = store.getPrices().stream()
                        .filter(price -> price.getProduct().equals(product))
                        .toList();

                while (startDate.isBefore(endDate)) {
                    final LocalDate filterDate = startDate;
                    Optional<ProductPrice> latestPrice = availablePrices.stream()
                            .filter(price -> price.getDate().isBefore(filterDate) || price.getDate().equals(filterDate))
                            .max(Comparator.comparing(ProductPrice::getDate));

                    if (latestPrice.isPresent()) {
                        int discount = 0;
                        final LocalDate discountStart = startDate;
                        final LocalDate discountEnd = discountStart.plusDays(daysBetween);
                        Optional<ProductDiscount> latestDiscount = discounts.stream()
                                .filter(d -> d.getStore().equals(store))
                                .filter(d -> !d.getEndDate().isBefore(discountStart) && !d.getStartDate().isAfter(discountEnd))
                                .findFirst();
                        if (latestDiscount.isPresent()) {
                            discount = latestDiscount.get().getDiscount();
                        }
                        allPricePoints.add(new PricePointDTO(
                                startDate,
                                latestPrice.get().getPrice(),
                                discount,
                                storeMapper.storeToDTO(store)
                        ));
                    }
                    startDate = startDate.plusDays(daysBetween);
                }
            }
            result.add(new ProductPriceHistoryDTO(
                    productMapper.productToDTO(product),
                    allPricePoints
            ));
        }

        return result;
    }
}
