package com.demo.controller;

import com.demo.dto.BasketDTO;
import com.demo.service.implementations.BasketService;
import com.demo.service.implementations.ShoppingListService;
import com.demo.service.interfaces.IBasketService;
import com.demo.service.interfaces.IShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final IShoppingListService shoppingListService;
    private final IBasketService basketService;

    public BasketController(ShoppingListService shoppingListService, BasketService basketService) {
        this.shoppingListService = shoppingListService;
        this.basketService = basketService;
    }

    @GetMapping
    public ResponseEntity<BasketDTO> getBasket() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(basketService.getBasket());
    }

    @PostMapping("/optimize")
    public ResponseEntity<String> optimizeBasket() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(shoppingListService.optimizeBasket());
    }
}
