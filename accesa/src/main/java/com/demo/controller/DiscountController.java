package com.demo.controller;

import com.demo.dto.DiscountDTO;
import com.demo.service.implementations.DiscountService;
import com.demo.service.interfaces.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final IDiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/best")
    public ResponseEntity<Page<DiscountDTO>> getBestDiscounts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(discountService.getBestDiscounts(page, size));
    }

    @GetMapping("/new")
    public ResponseEntity<Page<DiscountDTO>> getNewDiscounts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(discountService.getNewestDiscounts(page, size));
    }
}
