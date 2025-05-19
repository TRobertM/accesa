package com.demo.controller;

import com.demo.service.implementations.ShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppinglists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("/optimize")
    public ResponseEntity<String> optimizeBasket() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(shoppingListService.optimizeBasket());
    }
}
