package com.demo.controller;

import com.demo.dto.BasketDTO;
import com.demo.dto.UsernamePasswordDTO;
import com.demo.dto.UserDTO;
import com.demo.service.implementations.BasketService;
import com.demo.service.implementations.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final BasketService basketService;

    public UserController(UserService userService, BasketService basketService) {
        this.userService = userService;
        this.basketService = basketService;
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
}
