package com.demo.controller;

import com.demo.dto.CreatePriceAlertDTO;
import com.demo.dto.PriceAlertDTO;
import com.demo.service.implementations.PriceAlertService;
import com.demo.service.interfaces.IPriceAlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class PriceAlertController {

    private final IPriceAlertService priceAlertService;

    public PriceAlertController(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    @PostMapping("/create")
    public ResponseEntity<PriceAlertDTO> createPriceAlert(@RequestBody CreatePriceAlertDTO createPriceAlertDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(priceAlertService.createUserPriceAlert(createPriceAlertDTO));
    }

    @GetMapping
    public ResponseEntity<List<PriceAlertDTO>> getUserPriceAlerts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(priceAlertService.getUserPriceAlerts());
    }
}
