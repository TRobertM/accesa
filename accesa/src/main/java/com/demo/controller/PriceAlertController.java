package com.demo.controller;

import com.demo.dto.CreatePriceAlertDTO;
import com.demo.dto.PriceAlertDTO;
import com.demo.service.implementations.PriceAlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
public class PriceAlertController {

    private final PriceAlertService priceAlertService;

    public PriceAlertController(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    @PostMapping("/create")
    @GetMapping
    public ResponseEntity<PriceAlertDTO> getProductPriceHistory(@RequestBody CreatePriceAlertDTO createPriceAlertDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(priceAlertService.createUserPriceAlert(createPriceAlertDTO));
    }
}
