package com.demo.controller;

import com.demo.dto.*;
import com.demo.service.implementations.AuthService;
import com.demo.service.interfaces.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    private final IAuthService userService;

    public AuthenticationController(AuthService userService) {
        this.userService = userService;
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
}
