package com.demo.controller;

import com.demo.dto.ShoppingListDTO;
import com.demo.service.implementations.ShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingListDTO>> getShoppingLists() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(shoppingListService.getShoppingLists());
    }
}
