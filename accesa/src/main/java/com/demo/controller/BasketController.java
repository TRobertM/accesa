package com.demo.controller;

import com.demo.service.implementations.ShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final ShoppingListService shoppingListService;

    public BasketController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PatchMapping("/optimize")
    public ResponseEntity<String> optimizeCart() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(shoppingListService.optimizeBasket());
    }

}
