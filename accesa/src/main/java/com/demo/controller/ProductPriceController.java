package com.demo.controller;

import com.demo.dto.PriceHistoryRequestDTO;
import com.demo.dto.ProductPriceHistoryDTO;
import com.demo.service.implementations.ProductPriceService;
import com.demo.service.interfaces.IProductPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class ProductPriceController {

    private final IProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<ProductPriceHistoryDTO>> getProductPriceHistory(
            @RequestParam(required = false) Integer daysBetweenPoints,
            @RequestParam(required = false) String productId,
            @RequestParam(required = false) String storeId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam LocalDate startDate
    ) {
        PriceHistoryRequestDTO requestDTO = new PriceHistoryRequestDTO(
                Optional.ofNullable(daysBetweenPoints),
                Optional.ofNullable(productId),
                Optional.ofNullable(storeId),
                Optional.ofNullable(category),
                Optional.ofNullable(brand),
                startDate
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productPriceService.getPriceHistory(requestDTO));
    }
}
