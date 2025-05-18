package com.demo.controller;

import com.demo.dto.PriceHistoryRequestDTO;
import com.demo.dto.ProductPriceHistoryDTO;
import com.demo.service.implementations.ProductPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/price")
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @GetMapping
    public ResponseEntity<List<ProductPriceHistoryDTO>> getProductPriceHistory(@RequestBody PriceHistoryRequestDTO priceHistoryRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productPriceService.getPriceHistory(priceHistoryRequestDTO));
    }
}
