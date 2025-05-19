package com.demo.controller;

import com.demo.dto.*;
import com.demo.service.implementations.BasketService;
import com.demo.service.implementations.PriceAlertService;
import com.demo.service.implementations.ShoppingListService;
import com.demo.service.implementations.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final BasketService basketService;
    private final PriceAlertService priceAlertService;
    private final ShoppingListService shoppingListService;

    public UserController(UserService userService, BasketService basketService, PriceAlertService priceAlertService, ShoppingListService shoppingListService) {
        this.userService = userService;
        this.basketService = basketService;
        this.priceAlertService = priceAlertService;
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsernamePasswordDTO loginRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.loginUser(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UsernamePasswordDTO registerRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.registerUser(registerRequest));
    }

    @GetMapping("/basket")
    public ResponseEntity<BasketDTO> getBasket() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(basketService.getBasket());
    }

    @GetMapping("/shoppinglists")
    public ResponseEntity<List<ShoppingListDTO>> getShoppingLists() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(shoppingListService.getShoppingLists());
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<PriceAlertDTO>> getAlerts() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(priceAlertService.getUserPriceAlerts());
    }
}
